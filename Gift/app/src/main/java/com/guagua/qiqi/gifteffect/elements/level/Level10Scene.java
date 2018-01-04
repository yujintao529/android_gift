package com.guagua.qiqi.gifteffect.elements.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;

import com.guagua.qiqi.gifteffect.R;
import com.guagua.qiqi.gifteffect.animation.CaculateCommonHandle;
import com.guagua.qiqi.gifteffect.animation.algorithm.MathCommonAlg;
import com.guagua.qiqi.gifteffect.animation.algorithm.RangeCommon;
import com.guagua.qiqi.gifteffect.animation.algorithm.StaticValue;
import com.guagua.qiqi.gifteffect.elements.BitmapOnDrawListener;
import com.guagua.qiqi.gifteffect.elements.BitmapShape;
import com.guagua.qiqi.gifteffect.elements.GiftInfoElement;
import com.guagua.qiqi.gifteffect.elements.IScene;
import com.guagua.qiqi.gifteffect.elements.InfoPanel;
import com.guagua.qiqi.gifteffect.elf.ElfFactory;
import com.guagua.qiqi.gifteffect.util.BitmapUtils;
import com.guagua.qiqi.gifteffect.util.PXUtils;

/*************************************************************************************
* Module Name: Level9Scene</br>
* File Name: <b>Level9Scene.java</b></br>
* Description: TODO</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class Level10Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level6Scene";

	private Path clipPath;//剪裁path

	public Level10Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		setmLastTime(5000);
	}

	public Level10Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		setmLastTime(5000);
	}

	private void initData() {
		Bitmap bitmap = null;
		BitmapShape bitmapShape = null;
		final int assembleBitmapHeight = mHeight / 6;
		final int assembleBitmapWidth = mWidth;
		final InfoPanel infoPanel = new InfoPanel(this, assembleBitmapWidth, assembleBitmapHeight);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.initComX(0, 0f);
		caculateCommonHandle.c_y = RangeCommon.build(mHeight, mHeight / 2, 300);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);//不渐变
		infoPanel.setCaculateCommonHandle(caculateCommonHandle);
//		addShape(infoPanel);
		final Rect assembleRect = infoPanel.getAssembleRange();
		Rect infoPanelRect = null;
		Rect measureRect = null;
		Path clipPath = null;
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_bg);
		if (bitmap != null) {
			//修改默认的北京基准点，默认居中
			final int bgWidth = bitmap.getWidth();
			final int bgHeight = bitmap.getHeight();
			infoPanelRect = infoPanel.setInfoPanelRect((assembleBitmapWidth - bgWidth) / 2, (assembleBitmapHeight - bgHeight) / 2, bgWidth
					+ (assembleBitmapWidth - bgWidth) / 2, bgHeight + (assembleBitmapHeight - bgHeight) / 2);
			measureRect = infoPanel.setMeasureRect(infoPanelRect.left + PXUtils.dp2px(mContext, 10), infoPanelRect.top + PXUtils.dp2px(mContext, 8),
					infoPanelRect.right - PXUtils.dp2px(mContext, 6), infoPanelRect.bottom - PXUtils.dp2px(mContext, 2));
			Path temp = new Path();
			temp.addRoundRect(new RectF(measureRect),
					new float[] { PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 12),
							PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 28) },
					Direction.CW);
			clipPath = infoPanel.setClipPath(temp);
			//初始化背景数据
			BitmapShape bg = new BitmapShape(bitmap, this);
			ElfFactory.endowBackgroup(bg, 1f, infoPanelRect.left, infoPanelRect.top);
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_bg_front);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBackgroupBack(bitmapShape, (assembleBitmapWidth - bitmap.getWidth()) / 2,
						(assembleBitmapHeight - bitmap.getHeight()) / 2, 0f, .1f);
				infoPanel.addInnerElement(bitmapShape);
			}
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_rose);
			if (bitmap != null) {
				initRose(bitmap, measureRect, infoPanel);
			}
			//初始化背景里渐变的
			infoPanel.addInnerElement(bg);
		}

		//初始化星星数据,位图本身有长度和高度,同时,图片draw是以做上角为基准点的.
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_star);
		if (bitmap != null) {
			for (int i = 0; i < 5; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				//星星在背景  
				Rect temp = new Rect();
				temp.left = infoPanelRect.left - PXUtils.dp2px(mContext, 20);
				temp.right = infoPanelRect.right - PXUtils.dp2px(mContext, 20);
				temp.top = infoPanelRect.top - PXUtils.dp2px(mContext, 20);
				temp.bottom = infoPanelRect.bottom - PXUtils.dp2px(mContext, 10);
				ElfFactory.endowSimpleCollideBox(bitmapShape, temp, MathCommonAlg.rangeRandom(40, 200), MathCommonAlg.rangeRandom(20, 100));
				infoPanel.addInnerElement(bitmapShape);
			}
		}

//		//初始化红心
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_red_heart);
		if (bitmap != null) {
			for (int i = 0; i < 10; i++) {
				//因为对图片进行了缩放，所以如果以中心点为缩放点的话，会造成，范围误差
				bitmapShape = new BitmapShape(bitmap, this) {
					@Override
					protected float anchorScaleX() {
						return 0f;
					}

					@Override
					protected float anchorScaleY() {
						return 0f;
					}
				};

				ElfFactory.endowAnyWhereAlpha(bitmapShape, 0.4f, 4, infoPanelRect);
				infoPanel.addInnerElement(bitmapShape);
			}
		}
//			//制作特殊的红心
		Rect specialRedHeart = new Rect();
		specialRedHeart.left = measureRect.left - PXUtils.dp2px(mContext, 15);
		specialRedHeart.right = measureRect.left + PXUtils.dp2px(mContext, 15);
		specialRedHeart.top = measureRect.top - measureRect.height() > 0 ? measureRect.top - measureRect.height() : 0;
		specialRedHeart.bottom = measureRect.top - PXUtils.dp2px(mContext, 2);

		final float scale = .25f;
		for (int i = 0; i < 2; i++) {
			BitmapShape b1 = new BitmapShape(bitmap, this);
			BitmapShape b2 = new BitmapShape(bitmap, this);
			BitmapShape b3 = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel6SpecialRedHeart(b1, b2, b3, new Rect(specialRedHeart), 1000 + i * 1800, scale, bitmap.getWidth(),
					bitmap.getHeight());
			infoPanel.addInnerElement(b1);
			infoPanel.addInnerElement(b2);
			infoPanel.addInnerElement(b3);
		}
		//向上漂的白色物体
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_snowflake);
		if (bitmap != null) {
			for (int i = 0; i < 15; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBubbleUp(bitmapShape, MathCommonAlg.randomFloat(0.2f, 0.5f), measureRect);
				infoPanel.addInnerElement(bitmapShape);
			}
		}
//
		//初始化星星级别. 
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_s);
		if (bitmap != null) {
			for (int i = 0; i < 6; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, measureRect.left + PXUtils.dp2px(mContext, 16 + i * 10),
						measureRect.top - PXUtils.dp2px(mContext, 3), mContext);
				infoPanel.addInnerElement(bitmapShape);
			}
		}
//
		if (sceneInfo != null) {
			GiftInfoElement element = new GiftInfoElement(this, sceneInfo, measureRect);
			infoPanel.addInnerElement(element);
		}


		

		bitmap = null;
	}

	/**
	 * @param bitmap
	 * @return: void
	*/
	private void initRose(final Bitmap bitmap, final Rect rect, final InfoPanel infoPanel) {
		BitmapShape bitmapShape;
		//下面是常态的玫瑰花。
		Rect roseRect = BitmapUtils.correctBitmapRect(bitmap, rect, .6f);
		//玫瑰花所需要的背景区域
		roseRect.left = roseRect.left + PXUtils.dp2px(mContext, 20);
		roseRect.right = roseRect.right - PXUtils.dp2px(mContext, 20);
		roseRect.bottom = roseRect.top + PXUtils.dp2px(mContext, 20);
		//向上方向
		for (int i = 0; i < 10; i++) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel6AlwaysRose(bitmapShape, rect, roseRect, mContext, true);
			infoPanel.addInnerElement(bitmapShape);
		}
		//向下方向
		roseRect = BitmapUtils.correctBitmapRect(bitmap, rect, .6f);
		roseRect.left = roseRect.left + PXUtils.dp2px(mContext, 20);
		roseRect.right = roseRect.right - PXUtils.dp2px(mContext, 20);
		roseRect.top = roseRect.bottom - PXUtils.dp2px(mContext, 20);
		roseRect.bottom = roseRect.bottom - PXUtils.dp2px(mContext, 10);
		for (int i = 0; i < 10; i++) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel6AlwaysRose(bitmapShape, rect, roseRect, mContext, false);
			infoPanel.addInnerElement(bitmapShape);
		}
		//往前冲的玫瑰花
		roseRect = BitmapUtils.correctBitmapRect(bitmap, rect, .6f);
		final int top = roseRect.top;
		roseRect.top = top - PXUtils.dp2px(mContext, 10);
		roseRect.bottom = top;//+PXUtils.dp2px(mContext, 10);
		for (int i = 0; i < 50; i++) {
			bitmapShape = new BitmapShape(bitmap, this) {
				@Override
				protected float anchorRotationX() {
					return 0;
				}

				@Override
				protected float anchorRotationY() {
					return 0;
				}
			};
			ElfFactory.endowLevel6Rose(bitmapShape, rect, roseRect, mContext);
			infoPanel.addInnerElement(bitmapShape);
		}
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
		return "Level6Scene [sceneInfo=" + sceneInfo + "]";
	}
}
