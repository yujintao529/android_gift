package com.guagua.qiqi.gifteffect.elements.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

import com.guagua.qiqi.gifteffect.R;
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
public class Level7Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level7Scene";
	private Path clipPath;//剪裁path
	private Rect rect;//素材范围

	//左边钱币蹦出来的位置
	private Rect specialRedHeart;

	public Level7Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		rect = new Rect();
		specialRedHeart = new Rect();
	}

	public Level7Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		rect = new Rect();
		specialRedHeart = new Rect();
	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_bg);
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
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_bg_bg);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				//debug测试使用
//				bitmapShape.setBitmapOnDrawListener(this);

				ElfFactory.endowBackgroupBack2(bitmapShape, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2, 0f, 0.2f, 0.8f, 1);
				addShape(bitmapShape);
			}
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_money_bi);
			if (bitmap != null) {
				for (int i = 0; i < 15; i++) {
					bitmapShape = new BitmapShape(bitmap, this);
					ElfFactory.endowLevel7BackgroundBiUp(bitmapShape, rect);
					addShape(bitmapShape);
				}
				for (int i = 0; i < 10; i++) {
					bitmapShape = new BitmapShape(bitmap, this);
					ElfFactory.endowLevel7BackgroundBiDown(bitmapShape, rect);
					addShape(bitmapShape);
				}
			}
			addShape(bg);
			//初始化 剪裁区
			initClipPathAndRect();
		}

		//初始化钱袋
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_money);
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel7MoneyDai(bitmapShape, rect);
			addShape(bitmapShape);
		}
		//初始化钱币
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_money_bi);
		if (bitmap != null) {
			for (int i = 0; i < 10; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel7MoneyBi(bitmapShape, specialRedHeart, 1000 + i * 50);
				addShape(bitmapShape);
			}
		}
		//初始化背景星星
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_6_star);
		if (bitmap != null) {
			Rect rectStar = new Rect(rect);
			rectStar.top = rectStar.top - bitmap.getHeight() / 2;
			rectStar.left = rectStar.left - bitmap.getWidth() / 2;
			rectStar.right = rectStar.right - bitmap.getWidth() / 2;
			rectStar.bottom = rectStar.bottom - bitmap.getHeight() / 2;
			for (int i = 0; i < 20; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowAnyWhereAlpha2(bitmapShape, .8f, 2, rectStar, 1);
				addShape(bitmapShape);
			}
		}
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_yellow_circle);
		final Rect real=BitmapUtils.correctBitmapRect(bitmap, rect, 0.8f);
		real.bottom=real.top+PXUtils.dp2px(mContext, 10);
		for (int i = 0; i < 40; i++) {
			bitmapShape = new BitmapShape(bitmap,this);
			ElfFactory.endowLevel7YellowCircle(bitmapShape,rect, real,mContext,true);
			addShape(bitmapShape);
		}
		for (int i = 0; i < 40; i++) {
			bitmapShape = new BitmapShape(bitmap,this);
			ElfFactory.endowLevel7YellowCircle(bitmapShape,rect, real,mContext,false);
			addShape(bitmapShape);
		}
		
		//初始化星星级别.
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_s);
		if (bitmap != null) {
			for (int i = 0; i < 7; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, rect.left + PXUtils.dp2px(mContext, 18 + i * 10), rect.top - PXUtils.dp2px(mContext, 2),
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
		rect.top = rect.top + PXUtils.dp2px(mContext, 1);
		clipPath.addRoundRect(new RectF(rect),
				new float[] { 0, 0, PXUtils.dp2px(mContext, 6), PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 50),
						PXUtils.dp2px(mContext, 45), PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 18) }, Path.Direction.CW);

		//以rect为基准，这样的话，可以避免bg图片每次改变需要重新调整。只需要调整rect就行了
		specialRedHeart.left = rect.left - PXUtils.dp2px(mContext, 30);
		specialRedHeart.right = rect.left + PXUtils.dp2px(mContext, 30);
		specialRedHeart.top = rect.top - mBGRect.height() > 0 ? rect.top - mBGRect.height() : 0;
		specialRedHeart.bottom = rect.top - PXUtils.dp2px(mContext, 2);

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
		testPaint.setStyle(Paint.Style.STROKE);
		canvas.save();
//		canvas.clipPath(clipPath);
//		canvas.drawPath(clipPath, paint);
		canvas.drawRect(mBGRect, testPaint);
		canvas.drawPath(clipPath, testPaint);
		canvas.drawRect(rect, testPaint);
		canvas.drawRect(specialRedHeart, testPaint);
		canvas.drawBitmap(bitmap, matrix, paint);
		
		canvas.restore();

		return false;
	}

	@Override
	public String toString() {
		return "Level7Scene [sceneInfo=" + sceneInfo + "]";
	}

}
