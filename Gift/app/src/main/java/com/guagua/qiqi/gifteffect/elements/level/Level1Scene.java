package com.guagua.qiqi.gifteffect.elements.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
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
public class Level1Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level1Scene";

	private Path clipPath;//剪裁path
	private Rect rect;//素材范围

	public Level1Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		rect = new Rect();
		setmLastTime(2000);
	}

	public Level1Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		rect = new Rect();
		setmLastTime(2000);
	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_1_bg);
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
			//初始化背景后面的心跳动画
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_1_bg_front);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);

				ElfFactory.endowBackgroupBack(bitmapShape, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2, 0f,.1f);
				addShape(bitmapShape);
			}
			addShape(bg);
			//初始化 剪裁区
			initClipPathAndRect();
			//初始化背景前面的一个alpha渐变
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_1_bg_alpha);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBackgroupAlpha(bitmapShape, (mWidth - bitmap.getWidth()) / 2, (mHeight - bitmap.getHeight()) / 2);
				addShape(bitmapShape);
			}
		}

		//初始化跑动的黄线.
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_1_yellow_line);
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel1YellowLine(bitmapShape, rect.left + PXUtils.dp2px(mContext, 5), rect.top, rect);
			bitmapShape.setBitmapOnDrawListener(this);
			addShape(bitmapShape);
		}

		//初始化星星个数,星星不在剪裁范围里,所以不要设置setBitmapOnDrawListener
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_3_s);
		if (bitmap != null) {
			for (int i = 0; i < 1; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, rect.left + PXUtils.dp2px(mContext, 15 + i * 10), rect.top - PXUtils.dp2px(mContext, 4),
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

	//这个主要是因为所给的图片经常变，所以自定义一个大小避免重复修改
	private void initClipPathAndRect() {
		rect.set(mBGRect);
		rect.top = rect.top + PXUtils.dp2px(mContext, 12);
		rect.left = rect.left + PXUtils.dp2px(mContext, 10);
		rect.bottom = rect.bottom - PXUtils.dp2px(mContext, 12);
		rect.right = rect.right - PXUtils.dp2px(mContext, 12);
		clipPath.addRoundRect(new RectF(rect),
				new float[] { PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10),
						PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 60), PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 30) },
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
		return "Level1Scene [sceneInfo=" + sceneInfo + "]";
	}

}
