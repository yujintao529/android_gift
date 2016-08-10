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
import com.guagua.qiqi.gifteffect.elements.BitmapOnDrawListener;
import com.guagua.qiqi.gifteffect.elements.BitmapShape;
import com.guagua.qiqi.gifteffect.elements.CircleShape;
import com.guagua.qiqi.gifteffect.elements.GiftInfoElement;
import com.guagua.qiqi.gifteffect.elements.IScene;
import com.guagua.qiqi.gifteffect.elf.ElfFactory;
import com.guagua.qiqi.gifteffect.util.BitmapUtils;
import com.guagua.qiqi.gifteffect.util.PXUtils;

public class Level3Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level3Scene";
	private Path clipPath;//剪裁path
	private Rect rect;//素材范围

	public Level3Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		rect = new Rect();
		setmLastTime(3000);
	}

	public Level3Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		rect = new Rect();
	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_3_bg);
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
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_3_bg_front);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				//测试使用
//				bitmapShape.setBitmapOnDrawListener(this);
				ElfFactory.endowBackgroupBack(bitmapShape, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2, .2f,.4f);
				addShape(bitmapShape);
			}
			addShape(bg);
			//初始化 剪裁区
			initClipPathAndRect();
		}
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_2_white_line);
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevelCommonLine(bitmapShape, rect.left, rect.top , rect);
			addShape(bitmapShape);
		}
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_1_yellow_line );
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevelCommonLine(bitmapShape, rect.left, rect.top , rect);
			addShape(bitmapShape);
		}
		
		//白色气泡
		CircleShape circleShape;
		final int circleRadius=PXUtils.dp2px(mContext, 4);
		final Rect real=new Rect(rect);
		real.right=real.right-circleRadius;
		real.bottom=real.top+PXUtils.dp2px(mContext,5);
		for (int i = 0; i < 80; i++) {
			circleShape = new CircleShape(this,circleRadius);
			circleShape.setColor(Color.WHITE);
			ElfFactory.endowLevel3YellowCircle(circleShape, rect,real, mContext);
			addShape(circleShape);
		}
		//初始话星星级别
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_3_s);
		if (bitmap != null) {
			for (int i = 0; i < 3; i++) {
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
		rect.top = rect.top + PXUtils.dp2px(mContext, 18);
		rect.left = rect.left + PXUtils.dp2px(mContext, 10);
		rect.bottom = rect.bottom - PXUtils.dp2px(mContext, 14);
		rect.right = rect.right - PXUtils.dp2px(mContext, 6);
		clipPath.addRoundRect(new RectF(rect),
				new float[] { PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10),
						PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 40) },
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
		canvas.drawRect(mBGRect, testPaint);
		canvas.drawPath(clipPath, testPaint);
		canvas.drawBitmap(bitmap, matrix, paint);
		canvas.restore();
		return false;
	}

	@Override
	public String toString() {
		return "Level13cene [sceneInfo=" + sceneInfo + "]";
	}

}
