package com.guagua.qiqi.gifteffect.elements.level;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
import com.guagua.qiqi.gifteffect.util.VibratorManager;

/*************************************************************************************
* Module Name: Level8Scene</br>
* File Name: <b>Level8Scene.java</b></br>
* Description: TODO</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class Level8Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level7Scene";


	public Level8Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		setmLastTime(6000);
	}

	public Level8Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		setmLastTime(6000);
	}

	private void initData() {

		final int assembleBitmapHeight = mHeight / 6;
		final int assembleBitmapWidth = mWidth;
		final InfoPanel infoPanel = new InfoPanel(this, assembleBitmapWidth, assembleBitmapHeight);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x=StaticValue.build(0);
		caculateCommonHandle.c_y = StaticValue.build(mHeight/2);//RangeCommon.build(mHeight, mHeight / 2, 300);
		caculateCommonHandle.c_rotation=StaticValue.build(0);
//		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_scale_y=RangeCommon.build(0, 1,500);
		caculateCommonHandle.c_scale_x=RangeCommon.build(0, 1,500);
//		caculateCommonHandle.c_alpha=MulSimpleCal.build(new int[]{300,300,300,300,300},new float[]{100,-50,100,-50,150}, 0,MulEndMode.WITH_END_VALUE);//RangeCommon.build(0, 255,100);
		caculateCommonHandle.c_alpha=RangeCommon.build(0, 255, 1000);
		infoPanel.setCaculateCommonHandle(caculateCommonHandle);
		infoPanel.setStartOffset(4800);
		addShape(infoPanel);
		Rect infoPanelRect = null;	
		Rect measureRect = null;
		Path clipPath = null;
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_bg);
		BitmapShape bitmapShape;
		if (bitmap != null) {
			//修改默认的北京基准点，默认居中
			final int bgWidth = bitmap.getWidth();
			final int bgHeight = bitmap.getHeight();
			infoPanelRect = infoPanel.setInfoPanelRect((assembleBitmapWidth - bgWidth) / 2, (assembleBitmapHeight - bgHeight) / 2, bgWidth
					+ (assembleBitmapWidth - bgWidth) / 2, bgHeight + (assembleBitmapHeight - bgHeight) / 2);
			measureRect = infoPanel.setMeasureRect(infoPanelRect.left, infoPanelRect.top + PXUtils.dp2px(mContext, 1), infoPanelRect.right,
					infoPanelRect.bottom);
			Path temp = new Path();
			
			temp.addRoundRect(new RectF(measureRect),
					new float[] { 0, 0, PXUtils.dp2px(mContext, 6), PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 50),
							PXUtils.dp2px(mContext, 45), PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 18) }, Path.Direction.CW);
			clipPath = infoPanel.setClipPath(temp);

			//初始化背景数据
			BitmapShape bg = new BitmapShape(bitmap, this);
			ElfFactory.endowBackgroup(bg, 1f, infoPanelRect.left,infoPanelRect.top);
			//初始化背景里渐变的
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_bg_bg);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBackgroupBack2(bitmapShape,(assembleBitmapWidth-bitmap.getWidth())/2,
						(assembleBitmapHeight-bitmap.getHeight())/2, 0f, 0.2f, 0.8f, 1);
				infoPanel.addInnerElement(bitmapShape);
			}
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_bg_front);
			if (bitmap != null) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBackgroupBack(bitmapShape, (assembleBitmapWidth-bitmap.getWidth())/2,
						(assembleBitmapHeight-bitmap.getHeight())/2, 0f, .1f);
				infoPanel.addInnerElement(bitmapShape);
			}

			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_money_bi);
			if (bitmap != null) {
				for (int i = 0; i < 15; i++) {
					bitmapShape = new BitmapShape(bitmap, this);
					ElfFactory.endowLevel7BackgroundBiUp(bitmapShape, infoPanelRect);
					infoPanel.addInnerElement(bitmapShape);
				}
				for (int i = 0; i < 10; i++) {
					bitmapShape = new BitmapShape(bitmap, this);
					ElfFactory.endowLevel7BackgroundBiDown(bitmapShape, infoPanelRect);
					infoPanel.addInnerElement(bitmapShape);
				}
			}
			infoPanel.addInnerElement(bg);
		}
//		//初始化钱袋
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_money);
		if (bitmap != null) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel7MoneyDai(bitmapShape, measureRect);
			infoPanel.addInnerElement(bitmapShape);
		}

		//初始化钱币
		final Rect specialRect = new Rect();
		specialRect.left = measureRect.left - PXUtils.dp2px(mContext, 30);
		specialRect.right = measureRect.left + PXUtils.dp2px(mContext, 30);
		specialRect.top = measureRect.top - infoPanelRect.height() > 0 ? measureRect.top - infoPanelRect.height() : 0;
		specialRect.bottom = measureRect.top - PXUtils.dp2px(mContext, 2);

		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_money_bi);
		if (bitmap != null) {
			for (int i = 0; i < 10; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel7MoneyBi(bitmapShape, specialRect, 1000 + i * 50);
				infoPanel.addInnerElement(bitmapShape);
			}
		}
//		//初始化背景星星
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_6_star);
		if (bitmap != null) {
			Rect rectStar = new Rect(measureRect);
			rectStar.top = rectStar.top - bitmap.getHeight() / 2;
			rectStar.left = rectStar.left - bitmap.getWidth() / 2;
			rectStar.right = rectStar.right - bitmap.getWidth() / 2;
			rectStar.bottom = rectStar.bottom - bitmap.getHeight() / 2;
			for (int i = 0; i < 20; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowAnyWhereAlpha2(bitmapShape, .8f, 2, rectStar, 1);
				infoPanel.addInnerElement(bitmapShape);
			}
		}
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_7_yellow_circle);
		final Rect real = BitmapUtils.correctBitmapRect(bitmap, measureRect, 0.8f);
		real.bottom = real.top + PXUtils.dp2px(mContext, 10);
		for (int i = 0; i < 40; i++) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel7YellowCircle(bitmapShape, measureRect, real, mContext, true);
			infoPanel.addInnerElement(bitmapShape);
		}
		for (int i = 0; i < 40; i++) {
			bitmapShape = new BitmapShape(bitmap, this);
			ElfFactory.endowLevel7YellowCircle(bitmapShape, measureRect, real, mContext, false);
			infoPanel.addInnerElement(bitmapShape);
		}
//  
//		//初始化星星级别.
		bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_7_s);
		if (bitmap != null) {
			for (int i = 0; i < 7; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, measureRect.left + PXUtils.dp2px(mContext, 18 + i * 10),
						measureRect.top - PXUtils.dp2px(mContext, 2), mContext);
				infoPanel.addInnerElement(bitmapShape);
			}
		}
//
		if (sceneInfo != null) {
			GiftInfoElement element = new GiftInfoElement(this, sceneInfo, measureRect);
			infoPanel.addInnerElement(element);
		}
		
		
		{

			final int downStarOffset=500;//下坠星星的offsetTime
			final int downStarDuration=1500;//下坠星星的time
			final int showTimeOffset=1800;//横向光开始出现的
			final int rowLightShowTime=3000;//幸运横向光，向上移动时间
			final int starLightsTimeOffset=1800;//星蕴出现时间的offset
			final int starLightsTime=3000;//星蕴持续时间time
			final Rect panelRect=new Rect(0, mHeight/2,mWidth,mHeight/2+measureRect.height());
			RectF rectF = new RectF();
			rectF.left = 0;
			rectF.right = 30;
			rectF.bottom = 40;
			rectF.top = 0;
            //横向的光线，上线摆动
            //横向光线的rect区域
            bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_10_x3_n);
            if(bitmap!=null){
                bitmapShape=new BitmapShape(bitmap, this);
                ElfFactory.endowLevel10RowLight(bitmapShape, panelRect, showTimeOffset, rowLightShowTime);
                addShape(bitmapShape);
            }

            //星蕴
            bitmap=BitmapUtils.decodeBitmap(mContext, R.drawable.level_10_s3);
            for(int i=0;i<30;i++){
                bitmapShape=new BitmapShape(bitmap, this);
                ElfFactory.endowLevel10AlphaStars(bitmapShape, panelRect, MathCommonAlg.randomFloat(.8f, 2f), starLightsTimeOffset, starLightsTime);
                addShape(bitmapShape);
            }
			//下坠的流星
			for(int i=0;i<70;i++){
				bitmap=BitmapUtils.decodeBitmap(mContext,mContext.getResources().getIdentifier("level_10_d"+MathCommonAlg.rangeRandom(1, 20),"drawable",mContext.getPackageName()));
				bitmapShape=new BitmapShape(bitmap, this);
				ElfFactory.endowLevel10DownLight(bitmapShape, panelRect,downStarOffset,downStarDuration);
				addShape(bitmapShape);
			}
            for(int i=0;i<20;i++){
                bitmap=BitmapUtils.decodeBitmap(mContext,R.drawable.level_10_star);
                bitmapShape=new BitmapShape(bitmap, this);
                ElfFactory.endowLevel10DownLight(bitmapShape, panelRect,downStarOffset,downStarDuration);
                addShape(bitmapShape);
            }
			


		}
		
		
		
		bitmap = null;
	}


	@Override
	protected void onBeforeShow() {
		super.onBeforeShow();
		initData();
		VibratorManager.vibrator(mContext);
	}

	@Override
	protected void onAfterShow() {
		super.onAfterShow();
	}

	@Override
	public boolean draw(Canvas canvas, Matrix matrix, Paint paint, Bitmap bitmap, int timeDifference) {
		return true;
	}

	@Override
	public String toString() {
		return "Level7Scene [sceneInfo=" + sceneInfo + "]";
	}

}
