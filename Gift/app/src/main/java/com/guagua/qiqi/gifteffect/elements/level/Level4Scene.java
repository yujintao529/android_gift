package com.guagua.qiqi.gifteffect.elements.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;

import com.guagua.qiqi.gifteffect.R;
import com.guagua.qiqi.gifteffect.animation.algorithm.MathCommonAlg;
import com.guagua.qiqi.gifteffect.elements.BitmapOnDrawListener;
import com.guagua.qiqi.gifteffect.elements.BitmapShape;
import com.guagua.qiqi.gifteffect.elements.GiftInfoElement;
import com.guagua.qiqi.gifteffect.elements.IScene;
import com.guagua.qiqi.gifteffect.elf.ElfFactory;
import com.guagua.qiqi.gifteffect.util.BitmapUtils;
import com.guagua.qiqi.gifteffect.util.PXUtils;

/**
 * Created by jintao on 2015/7/2.
 */
public class Level4Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level4Scene";

	private Path clipPath;//剪裁path
	private Rect rect;//素材范围

	public Level4Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		rect = new Rect();
	}

	public Level4Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		rect = new Rect();
	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_4_bg);
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
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_4_bg_front);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				//debug测试使用
//				bitmapShape.setBitmapOnDrawListener(this);

				ElfFactory.endowBackgroupBack(bitmapShape, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2, 0f, 0.2f);
				addShape(bitmapShape);
			}
			addShape(bg);
			//初始化 剪裁区
			initClipPathAndRect();
		}

		//初始化雪花数据
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_4_snow);
		if (bitmap != null) {
			//标准的雪花
			for (int i = 0; i < 10; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel4SnowDown(bitmapShape, MathCommonAlg.randomFloat(0.5f, 1), MathCommonAlg.rangeRandom(1, 2), rect);
//				bitmapShape.setBitmapOnDrawListener(this);
				addShape(bitmapShape);
			}
			//落下一段后，消失的雪花
			for (int i = 0; i < 5; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel4SpecialSnowDown(bitmapShape, MathCommonAlg.randomFloat(1f, 1.5f), MathCommonAlg.rangeRandom(1, 2), rect);
//				bitmapShape.setBitmapOnDrawListener(this);
				addShape(bitmapShape);
			}
		}
		//初始化气泡数据
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_6_bubble);
		if (bitmap != null) {
			for (int i = 0; i < 15; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBubbleUp(bitmapShape, MathCommonAlg.randomFloat(0.5f, 1.5f), rect);
//				bitmapShape.setBitmapOnDrawListener(this);
				addShape(bitmapShape);
			}
		}

		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_4_light_point);
		if (bitmap != null) {
			//初始化横向跑动的小白。
			for (int i = 0; i < 5; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel4Snowball(bitmapShape, MathCommonAlg.randomFloat(0.6f, 0.8f), rect.top - PXUtils.dp2px(mContext, 4), rect.left
						+ PXUtils.dp2px(mContext, 40), rect);
//				bitmapShape.setBitmapOnDrawListener(this);
				addShape(bitmapShape);
			}
			//初始化下坠的小白。
			for (int i = 0; i < 10; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel4DropSnowball(bitmapShape, MathCommonAlg.randomFloat(0.5f, 1f), rect.top + 10, rect);
//				bitmapShape.setBitmapOnDrawListener(this);
				addShape(bitmapShape);
			}
		}
		//跑动的白线
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_4_white_line);
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevelCommonLine(bitmapShape, rect.left, rect.top - PXUtils.dp2px(mContext, 3.8f), rect);
			addShape(bitmapShape);
		}
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_4_s);
		if (bitmap != null) {
			for (int i = 0; i < 4; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, rect.left + PXUtils.dp2px(mContext, 10 + i * 10), rect.top - PXUtils.dp2px(mContext, 5),
						mContext);
				addShape(bitmapShape);
			}
		}

		if (sceneInfo != null) {
			GiftInfoElement element = new GiftInfoElement(this, sceneInfo);
			addShape(element);
		}

		bitmap = null;
	}

	private void initClipPathAndRect() {
		rect.set(mBGRect);
		rect.top = rect.top + PXUtils.dp2px(mContext, 5);
		clipPath.addRoundRect(new RectF(rect),
				new float[] { PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 12),
						PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 28), PXUtils.dp2px(mContext, 28) },
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

	Paint testPaint = new Paint();

	@Override
	public boolean draw(Canvas canvas, Matrix matrix, Paint paint, Bitmap bitmap, int timeDifference) {
		testPaint.setColor(Color.RED);
		testPaint.setStyle(Style.STROKE);
		paint.setStrokeWidth(3);
		paint.setAntiAlias(true);
		paint.setDither(true);
		canvas.save();
//		canvas.clipPath(clipPath);
//		canvas.drawPath(clipPath, paint);
//		canvas.drawRect(mBGRect, testPaint);
//		canvas.drawPath(clipPath, testPaint);
		canvas.drawBitmap(bitmap, matrix, paint);
		canvas.restore();
		return false;
	}

	@Override
	public String toString() {
		return "Level4Scene [sceneInfo=" + sceneInfo + "]";
	}

}
