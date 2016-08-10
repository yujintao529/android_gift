package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;

import com.guagua.qiqi.gifteffect.AnimationException;
import com.guagua.qiqi.gifteffect.animation.CaculateCommonHandle;

/*************************************************************************************
* Module Name: Element</br>
* File Name: <b>Element.java</b></br>
* Description: TODO</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public abstract class Element implements IAction {
	private static final String TAG = BitmapShape.class.getSimpleName();
	public static final int DURATION_ALWAYS = -1;
	private static final int canvasNumber = 0x00ffff;

	protected static final int ANIMATION_MODE_CANVAS = 1;
	protected static final int ANIMATION_MODE_MATRIX = 2;

	//是否计算矩阵
	private boolean mIsNeedMatrix;
	//矩阵所使用的anchor

	private float mScaleX;
	private float mScaleY;
	private float mRotationX;
	private float mRotationY;

	private CaculateCommonHandle caculateCommonHandle;

	//下面两个同时使用
	protected int mPeriod;//每次执行时间
	protected int mBlank;//执行完毕，空白时间（休息时间）。两个数想加就是每次动画的时间
	//动画开始偏移量和持续时间
	protected int duration;//持续时间,
	protected int mStartOffset;//开始的偏移量

	//画图所使用的矩阵和paint
	protected Matrix matrix;//
	protected Paint paint;//

	//场景宽高，方便使用
	protected int mSceneWidth;
	protected int mSceneHeight;

	//场景宿主对象
	protected IScene mIScene;

	private AnimationHandle mAnimationHandle;

	public Element(IScene iScene) {
		init(iScene, ANIMATION_MODE_MATRIX);
	}

	private void init(IScene iScene, int mode) {
		this.mIScene = iScene;
		duration = DURATION_ALWAYS;
		mSceneWidth = mIScene.mWidth;
		mSceneHeight = mIScene.mHeight;
		mStartOffset = 0;
		mPeriod = 0;
		matrix = new Matrix();
		paint = new Paint();
		mIsNeedMatrix = true;
		mAnimationHandle = build(this, mode);
	}

	public Element(IScene iScene, int Mode) {
		init(iScene, Mode);
	}

	protected void initAnchor() {
		mScaleX = anchorScaleX();
		mScaleY = anchorScaleY();
		mRotationX = anchorRotationX();
		mRotationY = anchorRotationY();
	}

	/**
	 * 矩阵计算。动画处理
	 *
	 * @param matrix
	 * @param paint
	 * @param timeDifference
	 * @return
	 */
	protected boolean valueMatrix(final Matrix matrix, final Paint paint, int timeDifference) {
		float value = 255;
		if (caculateCommonHandle.c_alpha != null) {
			value = caculateCommonHandle.c_alpha.caculate(timeDifference);
		}
		if (value <= 0) {
			return false;
		}

		paint.setAlpha((int) value);
		value = caculateCommonHandle.c_rotation.caculate(timeDifference);
		matrix.setRotate(value, mRotationX, mRotationY);
		//如果想部分缩放的话,设置c_scale_x和c_scale_y不设置c_scale
		if (caculateCommonHandle.c_scale != null) {
			float scale = caculateCommonHandle.c_scale.caculate(timeDifference);
			if (scale <= 0) {
				return false;
			}
			matrix.preScale(scale, scale, mScaleX, mScaleY);
		}
		else {
			float scalex = 1;
			float scaley = 1;
			if (caculateCommonHandle.c_scale_x != null) {
				scalex = caculateCommonHandle.c_scale_x.caculate(timeDifference);
			}
			if (caculateCommonHandle.c_scale_y != null) {
				scaley = caculateCommonHandle.c_scale_y.caculate(timeDifference);
			}
			matrix.preScale(scalex, scaley, mScaleX, mScaleY);
		}

		float x = caculateCommonHandle.c_x.caculate(timeDifference);
		if (x > mSceneWidth) {
			return false;
		}
		float y = caculateCommonHandle.c_y.caculate(timeDifference);
		if (y > mSceneHeight) {
			return false;
		}
		matrix.postTranslate(x, y);
		return true;
	}

	/**
	 * @param canvas         canvas画布
	 * @param timeDifference 时间差，采用/ms
	 *                       先简单点做，没有死亡的说法。如果计算结果为false ，则不进行绘制。先用持续时间作为死亡的标志
	 */
	@Override
	public void doDraw(Canvas canvas, int timeDifference) {
//        LogUtils.d(TAG, "ondraw time difference " + timeDifference + " duration " + duration);
		//如果时间到了，不在计算和绘制
		if ((duration != DURATION_ALWAYS && (duration + mStartOffset) < timeDifference)) {
			return;
		}
		if (timeDifference <= mStartOffset) {
			return;
		}
		int realStartTime = timeDifference - mStartOffset;
		//增加单元动画的能力
		if (mPeriod > 0 && mBlank >= 0) {
			realStartTime = realStartTime % (mPeriod + mBlank);
			if (realStartTime > mPeriod) {
				return;
			}
		}
		matrix.reset();
		canvas.save();
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG)); 
		if (mIsNeedMatrix && !mAnimationHandle.handle(canvas, matrix, paint, realStartTime)) {
			canvas.restore();
//        if(mIsNeedMatrix&&!valueMatrix(matrix,paint,realStartTime)){
			return;
		}
		draw(canvas, matrix, paint, realStartTime);
		canvas.restore();
	}

	public void setEnableMatrix(boolean enable) {
		mIsNeedMatrix = enable;
	}

	//产生矩阵变换所使用的anchor
	protected float anchorScaleX() {
		return 0;
	}

	protected float anchorScaleY() {
		return 0;
	}

	protected float anchorRotationX() {
		return 0;
	}

	protected float anchorRotationY() {
		return 0;
	}

	public int getSceneWidth() {
		return mSceneWidth;
	}

	public int getSceneHeight() {
		return mSceneHeight;
	}

	/**
	 * 单位毫秒
	 */
	public void setPeriod(int time) {
		if (time < 0)
			throw new AnimationException("period time must not litter 0");
		mPeriod = time;
	}

	/**
	 * 单位毫秒
	 */
	public void setBlank(int time) {
		if (time < 0)
			throw new AnimationException("blank time must not litter 0");
		mBlank = time;
	}

	public void setDuration(int time) {
		duration = time;
	}

	public void setCaculateCommonHandle(CaculateCommonHandle caculateCommonHandle) {
		this.caculateCommonHandle = caculateCommonHandle;
		if (this.caculateCommonHandle != null) {
			mIsNeedMatrix = true;
		}

	}

	public void setStartOffset(int time) {
		mStartOffset = time;
	}

	/**
	 * 回收资源
	 */
	protected void destroy() {
	}

	/**
	 * @param canvas         画布
	 * @param matrix
	 * @param paint
	 * @param timeDifference
	 */
	protected abstract void draw(Canvas canvas, Matrix matrix, Paint paint, int timeDifference);

	@Override
	public String toString() {
		return "Element{" + "mIsNeedMatrix=" + mIsNeedMatrix + ", caculateCommonHandle=" + caculateCommonHandle + ", mPeriod=" + mPeriod
				+ ", mBlank=" + mBlank + ", duration=" + duration + ", mStartOffset=" + mStartOffset + '}';

	}

	private static AnimationHandle build(Element element, int mode) {
		AnimationHandle animationHandle;
		switch (mode) {
			case ANIMATION_MODE_CANVAS:
				animationHandle = new CanvasHandle(element);
				break;
			case ANIMATION_MODE_MATRIX:
			default:
				animationHandle = new MatrixHandle(element);
				break;
		}
		return animationHandle;
	}

	/**
	 * 通过矩阵实现动画
	 */
	private static class MatrixHandle implements AnimationHandle {

		private Element element;

		public MatrixHandle(Element element) {
			this.element = element;
		}

		@Override
		public boolean handle(Canvas canvas, Matrix matrix, Paint paint, int timeDifference) {
			float value = 255;
			CaculateCommonHandle caculateCommonHandle = element.caculateCommonHandle;
			final float mRotationX = element.mRotationX;
			final float mRotationY = element.mRotationY;
			final float mScaleX = element.mScaleX;
			final float mScaleY = element.mScaleY;
			final float mSceneWidth = element.mSceneWidth;
			final float mSceneHeight = element.mSceneHeight;

			if (caculateCommonHandle.c_alpha != null) {
				value = caculateCommonHandle.c_alpha.caculate(timeDifference);
			}
			if (value <= 0) {
				return false;
			}

			paint.setAlpha((int) value);
			value = caculateCommonHandle.c_rotation.caculate(timeDifference);
			matrix.setRotate(value, mRotationX, mRotationY);
			//如果想部分缩放的话,设置c_scale_x和c_scale_y不设置c_scale
			if (caculateCommonHandle.c_scale != null) {
				float scale = caculateCommonHandle.c_scale.caculate(timeDifference);
				if (scale <= 0) {
					return false;
				}
				matrix.preScale(scale, scale, mScaleX, mScaleY);
			}
			else {
				float scalex = 1;
				float scaley = 1;
				if (caculateCommonHandle.c_scale_x != null) {
					scalex = caculateCommonHandle.c_scale_x.caculate(timeDifference);
				}
				if (caculateCommonHandle.c_scale_y != null) {
					scaley = caculateCommonHandle.c_scale_y.caculate(timeDifference);
				}
				matrix.preScale(scalex, scaley, mScaleX, mScaleY);
			}

			float x = caculateCommonHandle.c_x.caculate(timeDifference);
			if (x > mSceneWidth) {
				return false;
			}
			float y = caculateCommonHandle.c_y.caculate(timeDifference);
			if (y > mSceneHeight) {
				return false;
			}
			matrix.postTranslate(x, y);
			return true;
		}
	}

	/**
	 * 直接操作cavans实现动画
	 */
	private static class CanvasHandle implements AnimationHandle {

		private Element element;

		public CanvasHandle(Element element) {
			this.element = element;
		}

		@Override
		public boolean handle(Canvas canvas, Matrix matrix, Paint paint, int timeDifference) {
			float value = 255;
			CaculateCommonHandle caculateCommonHandle = element.caculateCommonHandle;
			final float mRotationX = element.mRotationX;
			final float mRotationY = element.mRotationY;
			final float mScaleX = element.mScaleX;
			final float mScaleY = element.mScaleY;
			final float mSceneWidth = element.mSceneWidth;
			final float mSceneHeight = element.mSceneHeight;

			if (caculateCommonHandle.c_alpha != null) {
				value = caculateCommonHandle.c_alpha.caculate(timeDifference);
			}
			if (value <= 0) {
				return false;
			}

			paint.setAlpha((int) value);
			if (caculateCommonHandle.c_color != null) {
				float color = caculateCommonHandle.c_color.caculate(timeDifference);
				paint.setColor((int) color);
			}

			//首先进行canvas移动
			float x = caculateCommonHandle.c_x.caculate(timeDifference);
			if (x > mSceneWidth) {
				return false;
			}
			float y = caculateCommonHandle.c_y.caculate(timeDifference);
			if (y > mSceneHeight) {
				return false;
			}
			canvas.translate(x, y);

			//然后计算旋转
			value = caculateCommonHandle.c_rotation.caculate(timeDifference);
			canvas.rotate(value, mRotationX, mRotationY);

			//最后进行所方法
			//如果想部分缩放的话,设置c_scale_x和c_scale_y不设置c_scale
			if (caculateCommonHandle.c_scale != null) {
				float scale = caculateCommonHandle.c_scale.caculate(timeDifference);
				if (scale <= 0) {
					return false;
				}
				canvas.scale(scale, scale, mScaleX, mScaleY);
			}
			else {
				float scalex = 1;
				float scaley = 1;
				if (caculateCommonHandle.c_scale_x != null) {
					scalex = caculateCommonHandle.c_scale_x.caculate(timeDifference);
				}
				if (caculateCommonHandle.c_scale_y != null) {
					scaley = caculateCommonHandle.c_scale_y.caculate(timeDifference);
				}
				canvas.scale(scalex, scaley, mScaleX, mScaleY);
			}
			return true;
		}
	}

	private interface AnimationHandle {
		boolean handle(Canvas canvas, Matrix matrix, Paint paint, int timeDifference);
	}

}
