package com.guagua.qiqi.gifteffect.elements.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;

import com.guagua.qiqi.gifteffect.R;
import com.guagua.qiqi.gifteffect.elements.BitmapOnDrawListener;
import com.guagua.qiqi.gifteffect.elements.BitmapShape;
import com.guagua.qiqi.gifteffect.elements.CircleShape;
import com.guagua.qiqi.gifteffect.elements.GiftInfoElement;
import com.guagua.qiqi.gifteffect.elements.IScene;
import com.guagua.qiqi.gifteffect.elf.ElfFactory;
import com.guagua.qiqi.gifteffect.util.BitmapUtils;
import com.guagua.qiqi.gifteffect.util.PXUtils;

public class Level5Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level5Scene";

	private Path clipPath;//剪裁path
	private Rect rect;//素材范围

	public Level5Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		rect = new Rect();
		setmLastTime(4000);
	}

	public Level5Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		rect = new Rect();
		setmLastTime(4000);
	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_5_bg);
		BitmapShape bitmapShape;
		if (bitmap != null) {
			//修改默认的北京基准点，默认居中
			final int bgWidth = bitmap.getWidth();
			final int bgHeight = bitmap.getHeight();
			Rect rect = new Rect((mWidth - bgWidth) / 2, (mHeight - bgHeight) / 2, bgWidth + (mWidth - bgWidth) / 2, bgHeight + (mHeight - bgHeight)
					/ 2);
			setBGRect(rect);
			//初始化背景数据
			BitmapShape bg = new BitmapShape(bitmap, this);
			ElfFactory.endowBackgroup(bg, 1f, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2);
			bg.setEnableMatrix(true);
			//初始化背景里渐变的
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_bg_front);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				//测试使用

				ElfFactory.endowBackgroupBack(bitmapShape, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2, -.1f,0f);
				addShape(bitmapShape);
			}
			addShape(bg);
			//初始化 剪裁区
			initClipPathAndRect();
		}

//		初始化， 背景上面的随机的圆
//		Shader shader=new RadialGradient(10,10,10,Color.BLUE,Color.RED,TileMode.CLAMP);
		for (int i = 0; i < 20; i++) {
			CircleShape circleShape = new CircleShape(this, 4);
			circleShape.setColor(Color.WHITE);
			ElfFactory.endowLevel5BubbleUp(circleShape, rect, mContext);
			addShape(circleShape);
		}

		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_star);
		if (bitmap != null) {
			Rect star = BitmapUtils.correctBitmapRect(bitmap, rect, 0.8f);
			for (int i = 0; i < 6; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowAnyWhereAlpha2(bitmapShape, 0.8f, 4, star, 1);
				addShape(bitmapShape);
			}
		}
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_yellow_line);
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevelCommonLine(bitmapShape, rect.left, rect.top - PXUtils.dp2px(mContext, 6), rect);
			addShape(bitmapShape);
		}

		//初始化灯光，因为三个中心点一样，所以看成一个整体
		//这里需要调整left和right的位置。

		Bitmap light = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_light);
		Bitmap lightBottom = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_light_bottom);
		Bitmap star = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_star);
		Bitmap starYellow = BitmapUtils.decodeBitmap(mContext, R.drawable.level_5_yellow_start);
		if (light != null && lightBottom != null && star != null) {
			for (int i = 0; i < 9; i++) {
				BitmapShape lightShape = new BitmapShape(light, this) {
					@Override
					protected float anchorRotationY() {
						return 0;
					}
				};
				BitmapShape lightBottomShape = new BitmapShape(lightBottom, this);
				BitmapShape star1Shape = new BitmapShape(star, this);
				BitmapShape star2Shape;
				star2Shape = new BitmapShape(starYellow, this);

				ElfFactory.endowLevel5Light(lightShape, lightBottomShape, star1Shape, star2Shape, rect, i, mContext);
				addShape(lightShape);
				addShape(lightBottomShape);
				addShape(star1Shape);
				addShape(star2Shape);
			}
		}

		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_5_s);
		if (bitmap != null) {
			for (int i = 0; i < 5; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, rect.left + PXUtils.dp2px(mContext, 12 + i * 10), rect.top - PXUtils.dp2px(mContext, 3),
						mContext);
				addShape(bitmapShape);
			}
		}

		if (sceneInfo != null) {
			GiftInfoElement element = new GiftInfoElement(this, sceneInfo,mBGRect);
			addShape(element);
		}

		bitmap = null;
	}

	/**
	 * 初始化几个相对的
	 * @return: void
	*/
	private void initClipPathAndRect() {
		rect.set(mBGRect);
		clipPath.addRoundRect(new RectF(rect),
				new float[] { PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 14), PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 12),
						PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 36), PXUtils.dp2px(mContext, 28) },
				Direction.CW);
	}

	@Override
	protected void onBeforeShow() {
		super.onBeforeShow();
		initData();
	}

	@Override
	protected void onAfterShow() {
		super.onAfterShow();
	}

	@Override
	public boolean draw(Canvas canvas, Matrix matrix, Paint paint, Bitmap bitmap, int timeDifference) {
		
		canvas.clipPath(clipPath);
		return true;
	}

	@Override
	public String toString() {
		return "Level5Scene [sceneInfo=" + sceneInfo + "]";
	}
}
