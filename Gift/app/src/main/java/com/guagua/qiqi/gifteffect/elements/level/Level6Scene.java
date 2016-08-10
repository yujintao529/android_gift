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
public class Level6Scene extends IScene implements BitmapOnDrawListener {
	private static final String TAG = "Level6Scene";

	private Path clipPath;//剪裁path
	private Rect rect;//素材范围

	private Rect specialRedHeart;

	private Rect person;

	public Level6Scene(Context context, int number, int width, int height) {
		super(context, number, width, height);
		clipPath = new Path();
		rect = new Rect();
		specialRedHeart = new Rect();
		person = new Rect();
	}

	public Level6Scene(Context context, int width, int height) {
		this(context, 50, width, height);
		clipPath = new Path();
		rect = new Rect();
		specialRedHeart = new Rect();
		person = new Rect();
	}

	private void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.level_6_bg);
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
			initClipPathAndRect();
			//测试
//			bg.setBitmapOnDrawListener(this);
			bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_rose);
			if (bitmap != null) {
				initRose(bitmap);
			}
			//初始化背景里渐变的
			addShape(bg);
			//初始化 剪裁区

		}

		//初始化星星数据,位图本身有长度和高度,同时,图片draw是以做上角为基准点的.
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_star);
		if (bitmap != null) {
			for (int i = 0; i < 5; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				//星星在背景  
				Rect temp = new Rect();
				temp.left = rect.left - PXUtils.dp2px(mContext, 20);
				temp.right = rect.right - PXUtils.dp2px(mContext, 20);
				temp.top = rect.top - PXUtils.dp2px(mContext, 20);
				temp.bottom = rect.bottom - PXUtils.dp2px(mContext, 10);
				ElfFactory.endowSimpleCollideBox(bitmapShape, temp, MathCommonAlg.rangeRandom(40, 200), MathCommonAlg.rangeRandom(20, 100));
				addShape(bitmapShape);
			}
		}

		//初始化红心
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

				ElfFactory.endowAnyWhereAlpha(bitmapShape, 0.4f, 4, rect);
				addShape(bitmapShape);
			}
			//制作特殊的红心

			final float scale = .25f;
			for (int i = 0; i < 2; i++) {
				BitmapShape b1 = new BitmapShape(bitmap, this);
				BitmapShape b2 = new BitmapShape(bitmap, this);
				BitmapShape b3 = new BitmapShape(bitmap, this);
				ElfFactory.endowLevel6SpecialRedHeart(b1, b2, b3, new Rect(specialRedHeart), 1000 + i * 1800, scale, bitmap.getWidth(),
						bitmap.getHeight());
				addShape(b1);
				addShape(b2);
				addShape(b3);
			}
//			//下面动态的两个小人
//			BitmapShape boyBody=new BitmapShape(BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_boy_body),this);
//			BitmapShape boyHead=new BitmapShape(BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_boy_head),this);
//			BitmapShape boyLeg=new BitmapShape(BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_boy_leg),this);
//			BitmapShape girlBody=new BitmapShape(BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_girl_body),this);
//			BitmapShape girlHead=new BitmapShape(BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_girl_head),this);
//			BitmapShape girlLeg=new BitmapShape(BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_girl_leg),this);
//			//测量两个小人的范围
//			person.left=rect.left-PXUtils.dp2px(mContext,10);
//			person.right=rect.left+PXUtils.dp2px(mContext,20);
//			person.top=rect.top-PXUtils.dp2px(mContext, 4);
//			person.bottom=rect.top+PXUtils.dp2px(mContext,30);
//			//测试
//			int height=person.height();
//			person.top=person.bottom;
//			person.bottom=person.top+height;
//			ElfFactory.endowLevel6Person(boyBody, boyHead, boyLeg, girlBody, girlHead, girlLeg, person, mContext);
//			addShape(girlLeg);
		}
		//向上漂的白色物体
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_snowflake);
		if (bitmap != null) {
			for (int i = 0; i < 15; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowBubbleUp(bitmapShape, MathCommonAlg.randomFloat(0.2f, 0.5f), rect);
//				bitmapShape.setBitmapOnDrawListener(this);
				addShape(bitmapShape);
			}
		}

		//初始化星星级别. 
		bitmap = BitmapUtils.decodeBitmap(mContext, R.drawable.level_6_s);
		if (bitmap != null) {
			for (int i = 0; i < 6; i++) {
				bitmapShape = new BitmapShape(bitmap, this);
				ElfFactory.endowLevelStar(bitmapShape, rect.left + PXUtils.dp2px(mContext, 15 + i * 10), rect.top - PXUtils.dp2px(mContext, 5),
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
		rect.left = rect.left + PXUtils.dp2px(mContext, 8);
		rect.right = rect.right - PXUtils.dp2px(mContext, 8);
		rect.top = rect.top + PXUtils.dp2px(mContext, 8);
		clipPath.addRoundRect(new RectF(rect),
				new float[] { PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 10), PXUtils.dp2px(mContext, 12), PXUtils.dp2px(mContext, 12),
						PXUtils.dp2px(mContext, 30), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 40), PXUtils.dp2px(mContext, 28) },
				Direction.CW);

		//以rect为基准，这样的话，可以避免bg图片每次改变需要重新调整。只需要调整rect就行了
		specialRedHeart.left = rect.left - PXUtils.dp2px(mContext, 15);
		specialRedHeart.right = rect.left + PXUtils.dp2px(mContext, 15);
		specialRedHeart.top = rect.top - mBGRect.height() > 0 ? rect.top - mBGRect.height() : 0;
		specialRedHeart.bottom = rect.top - PXUtils.dp2px(mContext, 2);

	}

	/**
	 * @param bitmap
	 * @return: void
	*/
	private void initRose(final Bitmap bitmap) {
		BitmapShape bitmapShape;
		//下面是常态的玫瑰花。
		Rect roseRect=BitmapUtils.correctBitmapRect(bitmap, rect, .6f);
		//玫瑰花所需要的背景区域
		roseRect.left = roseRect.left + PXUtils.dp2px(mContext, 20);
		roseRect.right = roseRect.right - PXUtils.dp2px(mContext, 20);
		roseRect.bottom = roseRect.top + PXUtils.dp2px(mContext, 20);
		//向上方向
		for(int i=0;i<10;i++){
			bitmapShape=new BitmapShape(bitmap, this);
			ElfFactory.endowLevel6AlwaysRose(bitmapShape, rect, roseRect, mContext,true);
			addShape(bitmapShape);
		}
		//向下方向
		roseRect=BitmapUtils.correctBitmapRect(bitmap, rect, .6f);
		roseRect.left = roseRect.left + PXUtils.dp2px(mContext, 20);
		roseRect.right = roseRect.right - PXUtils.dp2px(mContext, 20);
		roseRect.top=roseRect.bottom-PXUtils.dp2px(mContext, 20);
		roseRect.bottom=roseRect.bottom-PXUtils.dp2px(mContext, 10);
		for(int i=0;i<10;i++){
			bitmapShape=new BitmapShape(bitmap, this);
			ElfFactory.endowLevel6AlwaysRose(bitmapShape, rect, roseRect, mContext,false);
			addShape(bitmapShape);
		}
		//往前冲的玫瑰花
		roseRect=BitmapUtils.correctBitmapRect(bitmap, rect, .6f);
		final int top=roseRect.top;
		roseRect.top=top-PXUtils.dp2px(mContext,10);
		roseRect.bottom=top;//+PXUtils.dp2px(mContext, 10);
		for(int i=0;i<50;i++){
			bitmapShape=new BitmapShape(bitmap, this){
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
			addShape(bitmapShape);
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

	Paint testPaint = new Paint();

	@Override
	public boolean draw(Canvas canvas, Matrix matrix, Paint paint, Bitmap bitmap, int timeDifference) {
		testPaint.setColor(Color.RED);
		testPaint.setStyle(Style.STROKE);
		canvas.save();
//		canvas.clipPath(clipPath);
//		canvas.drawPath(clipPath, paint);
//		canvas.drawRect(mBGRect, testPaint);
//		canvas.drawPath(clipPath, testPaint);
//		canvas.drawRect(rect, testPaint);
//		canvas.drawRect(specialRedHeart,testPaint);
		canvas.drawRect(person, testPaint);
		canvas.drawBitmap(bitmap, matrix, paint);

		canvas.restore();

		return false;
	}

	@Override
	public String toString() {
		return "Level6Scene [sceneInfo=" + sceneInfo + "]";
	}
}
