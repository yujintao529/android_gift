package com.guagua.qiqi.gifteffect.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import com.guagua.qiqi.gifteffect.animation.AnimationWrapper;
import com.guagua.qiqi.gifteffect.animation.algorithm.RangeCommon;
import com.guagua.qiqi.gifteffect.util.BitmapUtils;
import com.guagua.qiqi.gifteffect.util.Logger;


/*************************************************************************************
* Module Name: SceneProxy</br>
* File Name: <b>SceneProxy.java</b></br>
* Description: 场景代理类-提供画布，动画</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class SceneProxy implements ISignt {

	private IScene mIScene;

	private Board mBoard;

	private long mBeginTime;//动画开始时间

	private int mBeginLastTime;//开始动画持续时间

	private int mBeginLastPoint;//开始动画开始时间点

	private int mEndLastTime;//结束动画时间长度
	private int mEndLastPoint;//结束动画时间点

	private int mLastTime;//动画持续时间
	private int mSumTime;//总共的时间

	//用于开场动画和结束动画。后期不放在这了
	private AnimationWrapper mBeginAnimation;
	private AnimationWrapper mEndAnimation;
	private Matrix mMatrix;
	private Paint mPaint;

	//context
	private Context mContext;

	
	
	
	
	//测试代码
	private RectF test_rectf = new RectF();
	private Paint test_paint = new Paint();

	public SceneProxy(IScene iScene) {
		this.mIScene = iScene;
		mContext = this.mIScene.mContext;
		mLastTime = mIScene.getlastTime();
		mIScene.setmLastTime(IScene.LAST_FOREVER);
		mBeginLastTime = 500;
		mEndLastTime = 300;
		mMatrix = new Matrix();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
		mEndAnimation=new AnimationWrapper();
		mEndAnimation.setAlphaAnimation(RangeCommon.build(255, 0, 300));
	}
	
	
	private int mBGColor=0x00000000;
	public void setBGColor(int color){
		this.mBGColor=color;
	}
	
	@Override
	public boolean isPlayEnd() {
		return mIScene.isPlayEnd();
	}

	@Override
	public void playEnd() {
		mIScene.playEnd();
	}

	@Override
	public void play(Canvas canvas) {
		final int timeDifference = (int) (System.currentTimeMillis() - mBeginTime);
		if (timeDifference > mSumTime) {
			mIScene.playEnd();
			return;
		}
		mBoard.clear();
		mIScene.play(mBoard.mCanvas);
		//就这两个动画了
		if (timeDifference <= mBeginLastPoint && mBeginAnimation != null) {
			mBeginAnimation.execute(mMatrix, mPaint, timeDifference);
		}
		else if (timeDifference >= mEndLastPoint && mEndAnimation != null) {  
			mEndAnimation.execute(mMatrix, mPaint, timeDifference-mEndLastPoint);
		}
		canvas.drawBitmap(mBoard.mRealBoard, mMatrix, mPaint);

		//测试代码debug,画出红色边框
		test_rectf.set(0, 0, mIScene.mWidth, mIScene.mHeight);
		test_paint.setColor(Color.RED);
		test_paint.setStyle(Paint.Style.STROKE);
		test_paint.setStrokeWidth(4);
		canvas.setMatrix(mMatrix);
		canvas.drawRect(test_rectf, test_paint);
	}

	
	
	@Override
	public boolean readyForPlay() {
		try {
			mBeginTime = System.currentTimeMillis();
			mBoard = new Board(mIScene.mWidth, mIScene.mHeight);
			mBoard.setBGColor(mBGColor);
			Logger.d("mBoard width " + mIScene.mWidth + " height " + mIScene.mHeight + " " + this);
			mBeginLastPoint += mBeginLastTime;
			mEndLastPoint = mBeginLastTime + mLastTime;
			mSumTime = mBeginLastTime + mLastTime + mEndLastTime;
			mIScene.readyForPlay();
		}
		catch (Exception e) {
			Logger.d("播放准备错误" + " " + this, e);
			return false;
		}
		return true;
	}

	@Override
	public void destroy() {
		mIScene.destroy();
		BitmapUtils.destroy(mBoard.mRealBoard);

	}

	/**
	 * 下面都是set和get方法
	 */

	
	public int getmBeginLastPoint() {
		return mBeginLastPoint;
	}

	public void setmBeginLastPoint(int mBeginLastPoint) {
		this.mBeginLastPoint = mBeginLastPoint;
	}

	public AnimationWrapper getmBeginAnimation() {
		return mBeginAnimation;
	}

	public void setmBeginAnimation(AnimationWrapper mBeginAnimation) {
		this.mBeginAnimation = mBeginAnimation;
	}

	
	public int getmBeginLastTime() {
		return mBeginLastTime;
	}

	public void setmBeginLastTime(int mBeginLastTime) {
		this.mBeginLastTime = mBeginLastTime;
		
	}

	public int getmEndLastTime() {
		return mEndLastTime;
	}

	public void setmEndLastTime(int mEndLastTime) {
		this.mEndLastTime = mEndLastTime;
	}

	public int getmLastTime() {
		return mLastTime;
	}

	public void setmLastTime(int mLastTime) {
		this.mLastTime = mLastTime;
	}


	@Override
	public String toString() {
		return "SceneProxy{" + "mIScene=" + mIScene + ", mBeginLastTime=" + mBeginLastTime + ", mEndLastTime=" + mEndLastTime + ", mLastTime="
				+ mLastTime + '}';
	}
}
