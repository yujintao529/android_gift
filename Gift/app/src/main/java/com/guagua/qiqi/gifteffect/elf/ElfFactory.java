package com.guagua.qiqi.gifteffect.elf;

import android.content.Context;
import android.graphics.Rect;

import com.guagua.qiqi.gifteffect.animation.CaculateCommonHandle;
import com.guagua.qiqi.gifteffect.animation.algorithm.CollideBoxSimple;
import com.guagua.qiqi.gifteffect.animation.algorithm.Common;
import com.guagua.qiqi.gifteffect.animation.algorithm.CommonX;
import com.guagua.qiqi.gifteffect.animation.algorithm.Decay;
import com.guagua.qiqi.gifteffect.animation.algorithm.Decay2;
import com.guagua.qiqi.gifteffect.animation.algorithm.Decay2AWhile;
import com.guagua.qiqi.gifteffect.animation.algorithm.DecayAWhile;
import com.guagua.qiqi.gifteffect.animation.algorithm.DecayOpposite2;
import com.guagua.qiqi.gifteffect.animation.algorithm.DecayOpposite2AWhile;
import com.guagua.qiqi.gifteffect.animation.algorithm.EnumConStant;
import com.guagua.qiqi.gifteffect.animation.algorithm.MathCommonAlg;
import com.guagua.qiqi.gifteffect.animation.algorithm.MulSimpleCal;
import com.guagua.qiqi.gifteffect.animation.algorithm.MulStaticCal;
import com.guagua.qiqi.gifteffect.animation.algorithm.NeedleRotation;
import com.guagua.qiqi.gifteffect.animation.algorithm.OffsetCal;
import com.guagua.qiqi.gifteffect.animation.algorithm.RandomRangeAWhile;
import com.guagua.qiqi.gifteffect.animation.algorithm.StandarCal;
import com.guagua.qiqi.gifteffect.animation.algorithm.StaticValue;
import com.guagua.qiqi.gifteffect.animation.algorithm.TimeLoop;
import com.guagua.qiqi.gifteffect.animation.algorithm.TimeLoop2;
import com.guagua.qiqi.gifteffect.animation.algorithm.Wave2;
import com.guagua.qiqi.gifteffect.elements.BitmapShape;
import com.guagua.qiqi.gifteffect.elements.CircleShape;
import com.guagua.qiqi.gifteffect.elements.Element;
import com.guagua.qiqi.gifteffect.elements.PaintShape;
import com.guagua.qiqi.gifteffect.util.PXUtils;

/**
 * Created by yujintao on 15/7/4.
 */
public class ElfFactory {

	/**
	 *  1级别以的跑动的黄线
	 * @param bitmapShape
	 * @param left
	 * @param top
	 * @param rect
	 * @return: void
	 */

	public static void endowLevel1YellowLine(BitmapShape bitmapShape, float left, float top, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = Decay2.build(rect.width() / 2f, 2, left);
		caculateCommonHandle.c_y = StaticValue.build(top);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_alpha = TimeLoop2.build(1, 255, 0.8f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(2000);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(1, 2000));

	}

	/**
	 * 通用的跑动的线
	 * @param bitmapShape
	 * @param left
	 * @param top
	 * @param rect
	 * @return: void
	 */

	public static void endowLevelCommonLine(BitmapShape bitmapShape, float left, float top, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = Decay2.build(rect.width() / 2f, 2, left);
		caculateCommonHandle.c_y = StaticValue.build(top);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_alpha = TimeLoop2.build(1, 255, 0.8f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(1000, 2000));
		bitmapShape.setPeriod(2000);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(1000, 2000));

	}

	/**
	 * 随机在背景上出现，alpha渐变消失
	 */
	public static void endowAnyWhereAlpha(BitmapShape bitmapShape, float scale, int time, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = RandomRangeAWhile.build(rect.left, rect.right, time);
		caculateCommonHandle.c_y = RandomRangeAWhile.build(rect.top, rect.bottom, time);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.c_alpha = TimeLoop.build(time, EnumConStant.CommonRatio.ALPHA, 0, .5f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
	}

	/**
	 * level7中闪烁的星星
	 */
	public static void endowAnyWhereAlpha2(BitmapShape bitmapShape, float scale, int time, Rect rect, float startAlpha) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = RandomRangeAWhile.build(rect.left, rect.right, time);
		caculateCommonHandle.c_y = RandomRangeAWhile.build(rect.top, rect.bottom, time);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.c_alpha = TimeLoop2.build(time, 255, 0, startAlpha);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
	}

	/**
	 * level6级别中特殊的红心
	 */
	public static void endowLevel6SpecialRedHeart(BitmapShape b1, BitmapShape b2, BitmapShape b3, Rect rect, int timeOffset, float scale,
			float bitmapWidth, float bitmapHeight) {
		final int last = 1500;//每个动画时间
		final int blankTime = 4000;
		final float centerX = rect.left - bitmapWidth / 2;
		//首先是中心红色❤️。
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(rect.width() / 2f + rect.left - bitmapWidth / 2);
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { last }, new float[] { -rect.height() }, rect.bottom - bitmapHeight / 2,
				EnumConStant.MulEndMode.WITH_END_VALUE);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.c_alpha = MulSimpleCal.build(new int[] { last }, new float[] { -150 }, 150, EnumConStant.MulEndMode.WITH_END_VALUE);
		b1.setCaculateCommonHandle(caculateCommonHandle);
		b1.setStartOffset(timeOffset);
		b1.setPeriod(last);
		b1.setBlank(blankTime);

		caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { last }, new float[] { rect.width() / 2 },
				rect.left - bitmapWidth / 2 + rect.width() / 4, EnumConStant.MulEndMode.WITH_END_VALUE);// StaticValue.build(rect.width()/2f+rect.left-bitmapWidth/2);
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { last }, new float[] { -rect.height() * 0.75f }, rect.bottom - bitmapHeight / 2,
				EnumConStant.MulEndMode.WITH_END_VALUE);
		float value = (float) Math.toDegrees(Math.atan((rect.width() / 2) / (rect.height() * 0.75f)));
		caculateCommonHandle.c_rotation = StaticValue.build(value);
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.c_alpha = MulSimpleCal.build(new int[] { last }, new float[] { -150 }, 150, EnumConStant.MulEndMode.WITH_END_VALUE);
		b2.setCaculateCommonHandle(caculateCommonHandle);
		b2.setStartOffset(timeOffset + 500);
		b2.setPeriod(last);
		b2.setBlank(blankTime);

		caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { last }, new float[] { -rect.width() / 2 }, rect.width() / 2f + rect.left
				- bitmapWidth / 2, EnumConStant.MulEndMode.WITH_END_VALUE);
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { last }, new float[] { -rect.height() * 0.55f }, rect.bottom - bitmapHeight / 2,
				EnumConStant.MulEndMode.WITH_END_VALUE);//MulSimpleCal.build(new int[]{last},new float[]{-rect.height()+bitmapHeight},rect.bottom-bitmapHeight/2, EnumConStant.MulEndMode.WITH_END_VALUE);
		value = (float) Math.toDegrees(Math.atan((rect.width() / 2) / (rect.height() * -0.55f)));
		caculateCommonHandle.c_rotation = StaticValue.build(value);
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.c_alpha = MulSimpleCal.build(new int[] { last }, new float[] { -150 }, 150, EnumConStant.MulEndMode.WITH_END_VALUE);
		b3.setCaculateCommonHandle(caculateCommonHandle);
		b3.setStartOffset(timeOffset + 1000);
		b3.setPeriod(last);
		b3.setBlank(blankTime);

	}

	//雪花向下飞
	public static void endowSnowDown(BitmapShape bitmapShape, float scale, int last, float startX, float startY) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = DecayAWhile.build(MathCommonAlg.rangeRandom(10, 20), startX, EnumConStant.DecayRatio.SLOW_CLIP, last);
		caculateCommonHandle.c_y = DecayAWhile.build(MathCommonAlg.rangeRandom(10, 20), startY, EnumConStant.DecayRatio.SLOW_CLIP, last);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(last * 1000);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(0000, 2000));
	}

	/**
	 * 
	 * level4气泡向上飘
	 * @param element
	 * @param scale
	 * @param rect
	 * @return: void
	 */
	public static void endowBubbleUp(Element element, float scale, Rect rect) {
		int last = MathCommonAlg.rangeRandom(1000, 3000);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		float direction = MathCommonAlg.randomFloat(-1, 1);
		caculateCommonHandle.c_x = Decay2AWhile.build(rect.width() / 10 * direction, MathCommonAlg.randomFloat(0, 2),
				MathCommonAlg.rangeRandom(rect.left, rect.right), last / 1000f);
		caculateCommonHandle.c_y = DecayOpposite2AWhile.build(-1 / 2f, rect.bottom, last / 1000f);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);//不渐变
		element.setCaculateCommonHandle(caculateCommonHandle);
		element.setStartOffset(MathCommonAlg.rangeRandom(0, 1000));
		element.setPeriod(MathCommonAlg.rangeRandom(1000, 3000));
		element.setBlank(1000);
	}

	/**
	 * 设置背景
	 * @param element
	 * @param scale
	 * @param startX
	 * @param startY
	 * @return: void
	*/
	public static void endowBackgroup(Element element, float scale, float startX, float startY) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.initComX(startX, 0f);
		caculateCommonHandle.initComY(startY, 0f);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 1f, scale, scale);
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);//不渐变
		element.setCaculateCommonHandle(caculateCommonHandle);
	}

	/**
	 * 
	 * 背景后面的闪烁
	 * @return: void
	 */
	public static void endowBackgroupFront(Element element, float startX, float startY) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(startX);
		caculateCommonHandle.c_y = StaticValue.build(startY);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = Wave2.build(1, 1, -.2f, .2f, 1f / 2f);
		caculateCommonHandle.c_alpha = TimeLoop2.build(2, 255, 0.6f, 1);
		element.setCaculateCommonHandle(caculateCommonHandle);
	}

	/**
	 *
	 * 背景后面的闪烁
	 * @return: void
	 */
	public static void endowBackgroupAlpha(BitmapShape bitmapShape, float startX, float startY) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(startX);
		caculateCommonHandle.c_y = StaticValue.build(startY);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_alpha = TimeLoop2.build(2, 254, 0.0f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
	}

	/**
	 *  每个级别背景不一样大小。默认缩放大小为1
	 * @param element el
	 * @param startX  x位置
	 * @param startY  y位置
	 * @param startScale  波动的最小值，1＋最小值，就是背景的最小缩放值
	 * @param endScale 波动的最大值，1+最大值，就是背景的最大值缩放值
	 */
	public static void endowBackgroupBack2(Element element, float startX, float startY, float startScale, float endScale, float startAlpha,
			float endAlpha) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(startX);
		caculateCommonHandle.c_y = StaticValue.build(startY);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = Wave2.build(1, 1, startScale, endScale, 1f / 2f);
		caculateCommonHandle.c_alpha = TimeLoop2.build(2, 255, startAlpha, endAlpha);
		element.setCaculateCommonHandle(caculateCommonHandle);
	}

	/**
	 *  每个级别背景不一样大小。默认缩放大小为1
	 * @param element el
	 * @param startX  x位置
	 * @param startY  y位置
	 * @param startScale  波动的最小值，1＋最小值，就是背景的最小缩放值
	 * @param endScale 波动的最大值，1+最大值，就是背景的最大值缩放值
	 */
	public static void endowBackgroupBack(Element element, float startX, float startY, float startScale, float endScale) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(startX);
		caculateCommonHandle.c_y = StaticValue.build(startY);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = Wave2.build(1, 1, startScale, endScale, 1f / 2f);
		caculateCommonHandle.c_alpha = TimeLoop2.build(2, 255, 0.6f, 1);
		element.setCaculateCommonHandle(caculateCommonHandle);
	}

	/**
	 *
	 * @param element 元素
	 * @param rect 矩形
	 * @param xk x速率
	 * @param yk y速率
	 */
	public static void endowSimpleCollideBox(Element element, Rect rect, int xk, int yk) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		CollideBoxSimple simpleCollide = CollideBoxSimple.build(rect, xk, yk);
		caculateCommonHandle.c_x = simpleCollide.newModleX();
		caculateCommonHandle.c_y = simpleCollide.newModleY();
		caculateCommonHandle.c_rotation = StaticValue.build(110f);
		caculateCommonHandle.c_scale = Wave2.build(1, 1, -0.5f, 0, 1f);
//		caculateCommonHandle.c_scale = Wave.build(EnumConStant.CommonRatio.DEFAULT, 1, 0, MathCommonAlg.randomFloat(), 1,
//				MathCommonAlg.rangeRandom(2, 6), EnumConStant.WaveMode.MIDDLE, true);//PositiveAlpha.build(0f, 1f, 0.5f, 1f);
//        caculateCommonHandle.initComScale(0f, 0f, 1f, 1f);
//        caculateCommonHandle.initComAlpha(0f, 1f, 0.5f, 1f);//不渐变
//        caculateCommonHandle.initComScale(0f, 0f, scale, scale);
//		caculateCommonHandle.c_alpha = Wave.build(EnumConStant.CommonRatio.ALPHA, 1, 0, MathCommonAlg.randomFloat(), 1,
//				MathCommonAlg.rangeRandom(2, 6), EnumConStant.WaveMode.MIDDLE, true);//PositiveAlpha.build(0f, 1f, 0.5f, 1f);
		caculateCommonHandle.c_alpha = Wave2.build(255, 255, -0.5f, 0f, 1f);
		element.setCaculateCommonHandle(caculateCommonHandle);
		element.setStartOffset(MathCommonAlg.rangeRandom(0, 5000));
		element.setPeriod(15000);
		element.setBlank(3000);
	}

	public static void endowBgRose(Element element, float value, int last, float width, float height, float scale) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = Common.build(width, value, 1000f / last, 0, 1);
		caculateCommonHandle.initComY(MathCommonAlg.rangeRandom(50, 150), MathCommonAlg.rangeRandom(50, 100) * 1f / 1000f);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);
		caculateCommonHandle.initComScale(0f, 0f, 1f, 1f);
		caculateCommonHandle.initComAlpha(0f, 1f, 0.5f, 1f);//不渐变
		caculateCommonHandle.initComScale(0f, 0f, 1, 1);
//        caculateCommonHandle.c_alpha= Wave.build(EnumConStant.CommonRatio.ALPHA,1,0,0.5f,1,5, EnumConStant.WaveMode.SMALL,false);//PositiveAlpha.build(0f, 1f, 0.5f, 1f);
		element.setCaculateCommonHandle(caculateCommonHandle);
		element.setStartOffset((int) (value * 1000));
		element.setPeriod(last);
		element.setBlank(1000);
	}

	/**
	 * level4的标准雪花下落
	 * @param bitmapShape
	 * @param scale
	 * @param last
	 * @param rect
	 * @return: void
	*/
	public static void endowLevel4SnowDown(BitmapShape bitmapShape, float scale, int last, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = Wave2.build(rect.width(), MathCommonAlg.rangeRandom(rect.left, rect.right), 0f, 0.1f, 1);
//        caculateCommonHandle.c_x = DecayAWhile.build(MathCommonAlg.rangeRandom(10, 20), startX, EnumConStant.DecayRatio.SLOW_CLIP, last);
		caculateCommonHandle.c_y = Decay.build(rect.height(), rect.top, EnumConStant.DecayRatio.SLOW_CLIP);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(last * 1000);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(0000, 2000));
	}

	/**
	 * level4的雪花下落 折现后消失
	 * @param bitmapShape
	 * @param scale
	 * @param last
	 * @param rect
	 * @return: void
	*/
	public static void endowLevel4SpecialSnowDown(BitmapShape bitmapShape, float scale, int last, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
//		caculateCommonHandle.c_x = NewWave.build(rect.width(), MathCommonAlg.rangeRandom(rect.left, rect.right), 0f, 0.1f, 1);
//        caculateCommonHandle.c_x = DecayAWhile.build(MathCommonAlg.rangeRandom(10, 20), startX, EnumConStant.DecayRatio.SLOW_CLIP, last);
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { 500, 500, 800, 1000 }, new float[] { 20 * MathCommonAlg.rangeRandom(1, 3),
				20 * MathCommonAlg.rangeRandom(1, 3) * -1, 0, 40 * MathCommonAlg.rangeRandom(1, 3) }, rect.width() * MathCommonAlg.randomFloat());
//		caculateCommonHandle.c_y = Decay2.build(rect.height()/4,EnumConStant.DecayRatio.SLOWER_CLIP.value(), rect.top);
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 1000, 800, 1000 }, new float[] { 10 * MathCommonAlg.rangeRandom(1, 3), 0,
				20 * MathCommonAlg.rangeRandom(1, 3) }, rect.top);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//scale用传过来的值
		caculateCommonHandle.c_alpha = OffsetCal.build(1f, 255, MulSimpleCal.build(new int[] { 2000, 200 }, new float[] { -255, 0 }, 255));
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(4000);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(0000, 2000));
	}

	/**
	 * level4 中横向跑动的小白
	 * @param bitmapShape
	 * @param scale
	 * @param top
	 * @param rect
	 * @return: void
	 */
	public static void endowLevel4Snowball(BitmapShape bitmapShape, float scale, float top, float left, Rect rect) {
		int last = MathCommonAlg.rangeRandom(1000, 3000);
		float index = MathCommonAlg.randomFloat(0.1f, 1.5f);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = Decay2AWhile.build(rect.width() / 3, index, left, last / 1000f);
		caculateCommonHandle.initComY(top, 0);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(last);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(0000, 2000));
	}

	/**
	 * level4中特殊的雪花下落行为
	 * @param bitmapShape
	 * @param scale
	 * @param top
	 * @param rect
	 * @return: void
	 */
	public static void endowLevel4DropSnowball(BitmapShape bitmapShape, float scale, float top, Rect rect) {
		int last = MathCommonAlg.rangeRandom(1000, 3000);
		float index = MathCommonAlg.randomFloat(0.1f, 1.5f);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = Decay2.build(rect.width() / 3, index, rect.left);
//        caculateCommonHandle.c_x = DecayAWhile.build(MathCommonAlg.rangeRandom(10, 20), startX, EnumConStant.DecayRatio.SLOW_CLIP, last);
		caculateCommonHandle.initComX(MathCommonAlg.rangeRandom(rect.left, rect.right), 0);
		caculateCommonHandle.c_y = Decay2.build(rect.height() / 2, MathCommonAlg.randomFloat(0.5f, 1.5f), top);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(last);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(0020, 2000));
	}

	/**
	 * level2中蝴蝶行为有三种,简化操作,不采用计算方式,求方向
	 * @param bitmapShape
	 * @param rect
	 * @return: void
	 */
	public static void endowLevel4Hudie(BitmapShape bitmapShape, int what, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		switch (what) {
			case 1:
				caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { 2000, 4000, 1000 }, new float[] { 200, 200, 300 }, rect.left);
				caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
						100, 100, 100, 100, 100 }, new float[] { 1, 2, 3, 4, 5, 5, 4, 3, 2, 1, -1f, -2f, -3f, -3f, -3f, -2f, -1f, 0f, 0f, 0f },
						MathCommonAlg.randomFloat(rect.top, rect.top + rect.height() / 4));
				caculateCommonHandle.c_rotation = MulStaticCal.build(new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
						100, 100, 100, 100, 100, 100, 100 },
						new float[] { (float) (90 + Math.toDegrees(Math.atan(1f / 10f))), (float) (90 + Math.toDegrees(Math.atan(2f / 10f))),
								(float) (90 + Math.toDegrees(Math.atan(3f / 10f))), (float) (90 + Math.toDegrees(Math.atan(4f / 10f))),
								(float) (90 + Math.toDegrees(Math.atan(5f / 10f))), (float) (90 + Math.toDegrees(Math.atan(5f / 10f))),
								(float) (90 + Math.toDegrees(Math.atan(4f / 10f))), (float) (90 + Math.toDegrees(Math.atan(3f / 10f))),
								(float) (90 + Math.toDegrees(Math.atan(2f / 10f))), (float) (90 + Math.toDegrees(Math.atan(1f / 10f))),
								(float) (90 + Math.toDegrees(Math.atan(-1f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-2f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-3f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-3f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-3f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-2f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-1f / 5f))), (float) (90 + Math.toDegrees(Math.atan(0f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-0f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-0f / 5f))) }, 0);

				break;
			case 2:
				caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { 2000, 4000, 1000 }, new float[] { 400, 200, 300 }, rect.left);
				caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
						100, 100, 100, 100, 100 }, new float[] { -10, -10, -8, -8, -6, -6, -4, -2, 2, 4, 6, 8, 10, 10f, 8f, 6f, 4f, 2f, 0f, 0f },
						MathCommonAlg.rangeRandom(rect.top + rect.height() / 2, rect.bottom));

				caculateCommonHandle.c_rotation = MulStaticCal.build(new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
						100, 100, 100, 100, 100, 100, 100 },
						new float[] { (float) (90 + Math.toDegrees(Math.atan(-10f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-10f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-8f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-8f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-6f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-6f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(-4f / 5f))), (float) (90 + Math.toDegrees(Math.atan(-2f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(2f / 5f))), (float) (90 + Math.toDegrees(Math.atan(4f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(6f / 5f))), (float) (90 + Math.toDegrees(Math.atan(8f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(10f / 5f))), (float) (90 + Math.toDegrees(Math.atan(10f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(8f / 5f))), (float) (90 + Math.toDegrees(Math.atan(6f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(4f / 5f))), (float) (90 + Math.toDegrees(Math.atan(2f / 5f))),
								(float) (90 + Math.toDegrees(Math.atan(0f / 5f))), (float) (90 + Math.toDegrees(Math.atan(0f / 5f))) }, 0);
				break;
			case 3:
				caculateCommonHandle.c_x = Decay2.build(rect.width() / 4, MathCommonAlg.randomFloat(0.5f, 2f), rect.left);
				caculateCommonHandle.c_y = StaticValue.build(MathCommonAlg.rangeRandom(rect.top + rect.height() / 4, rect.top + rect.height() / 2));
				caculateCommonHandle.c_rotation = StaticValue.build(90);
				break;
			case 4:
			default:
				float y = MathCommonAlg.randomFloat(0.01f, 0.1f);
				caculateCommonHandle.initComX(MathCommonAlg.rangeRandom(rect.left + rect.width() / 4, rect.left + rect.width() / 2), 0.2f);
				caculateCommonHandle.initComY(rect.bottom, -y);
				caculateCommonHandle.c_rotation = StaticValue.build((float) (90 + Math.toDegrees(Math.atan(-y / .2f))));
				break;

		}
		caculateCommonHandle.c_scale_x = Wave2.build(1, 1, -0.8f, 0f, 5f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 3000));
		bitmapShape.setPeriod(4000);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(20, 2000));
	}

	/**
	 * level2中横向跑动的纹理
	 * @param bitmapShape
	 * @param scale
	 * @param rect
	 * @return: void
	*/
	public static void endowLevel2Texture(BitmapShape bitmapShape, float scale, Rect rect) {
		int last = MathCommonAlg.rangeRandom(1000, 3000);
		float index = MathCommonAlg.randomFloat(0.1f, 1.5f);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { last }, new float[] { rect.width() - 10 }, rect.left + 10);
		caculateCommonHandle.initComY(MathCommonAlg.rangeRandom((int) (rect.top + rect.height() * 0.1f), (int) (rect.top + rect.height() * 0.6f)), 0);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(last);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(2000, 4000));
	}

	/**
	 * level2中横向跑动的纹理
	 * @param bitmapShape
	 * @param scale
	 * @param rect
	 * @return: void
	 */
	public static void endowLevel2Texture2(BitmapShape bitmapShape, float scale, Rect rect) {
		int last = MathCommonAlg.rangeRandom(1000, 3000);
		float index = MathCommonAlg.randomFloat(0.1f, 1.5f);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { last }, new float[] { (rect.width() - 10) * -1 }, rect.right - 10);
		caculateCommonHandle.initComY(MathCommonAlg.rangeRandom((int) (rect.top + rect.height() * 0.1f), (int) (rect.top + rect.height() * 0.6f)), 0);
		caculateCommonHandle.initComRotation(0, 0, 0, 0);//不旋转
		caculateCommonHandle.initComScale(0f, 0f, scale, scale);//
		caculateCommonHandle.initComAlpha(0f, 0f, 1f, 1f);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(0, 2000));
		bitmapShape.setPeriod(last);
		bitmapShape.setBlank(MathCommonAlg.rangeRandom(2000, 4000));
	}

	/**
	 * level中星星级别
	 * @param bitmapShape
	 * @param top
	 * @return: void
	*/
	public static void endowLevelStar(BitmapShape bitmapShape, float left, float top, Context context) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(left);
		caculateCommonHandle.c_y = StaticValue.build(top);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_alpha = StaticValue.build(255);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);

	}

	/**
	 * level5灯光
	 * 主要是解决图片位置的问题。
	 * @param light
	 * @param bottom
	 * @param star1
	 * @param star2
	 * @param rect
	 * @param what
	 * @param context
	 */
	public static void endowLevel5Light(BitmapShape light, BitmapShape bottom, BitmapShape star1, BitmapShape star2, Rect rect, int what,
			Context context) {

		final int last = 2;
		CaculateCommonHandle lightCal = new CaculateCommonHandle();
		CaculateCommonHandle bottomCal = new CaculateCommonHandle();
		CaculateCommonHandle star1Cal = new CaculateCommonHandle();
		CaculateCommonHandle star2Cal = new CaculateCommonHandle();

		switch (what) {
			case 0://左上角
				lightCal.c_rotation = TimeLoop2.build(last, 360, -0.2f, -0.1f);
				bottomCal.c_rotation = StaticValue.build(-65);
				star1Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);
				lightCal.c_x = StaticValue.build(rect.left - PXUtils.dp2px(context, 16));
				lightCal.c_y = StaticValue.build(rect.top + PXUtils.dp2px(context, 3));

				bottomCal.c_x = StaticValue.build(rect.left - PXUtils.dp2px(context, 8));
				bottomCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 8));
				star1Cal.c_x = StaticValue.build(rect.left - PXUtils.dp2px(context, 18));
				star1Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 18));
				star2Cal.c_x = StaticValue.build(rect.left - PXUtils.dp2px(context, 18));
				star2Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 18));
				break;
			case 1:
				lightCal.c_rotation = TimeLoop2.build(8, 360, -0.1f, .1f);
				bottomCal.c_rotation = StaticValue.build(0);

				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(50, 100) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);

				lightCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 50));
				lightCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 3));

				bottomCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 55));
				bottomCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 12));

				star1Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 45));
				star1Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 22));

				star2Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 45));
				star2Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 22));
				break;
			case 2:
				lightCal.c_rotation = TimeLoop2.build(4, 180, -0.1f, .1f);
				bottomCal.c_rotation = StaticValue.build(0);
				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(50, 100) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);

				lightCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 110));
				lightCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 3));
				bottomCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 115));
				bottomCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 12));
				star1Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 105));
				star1Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 22));
				star2Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 105));
				star2Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 22));
				break;
			case 3:
				lightCal.c_rotation = TimeLoop2.build(8, 360, -0.1f, .1f);
				bottomCal.c_rotation = StaticValue.build(0);
				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(50, 100) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);
				lightCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 180));
				lightCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 3));
				bottomCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 185));
				bottomCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 12));
				star1Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 175));
				star1Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 22));
				star2Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 175));
				star2Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 22));
				break;
			case 4:
				lightCal.c_rotation = TimeLoop2.build(4, 360, 0.05f, 0.15f);
				bottomCal.c_rotation = StaticValue.build(30);
				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(50, 100) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);
				lightCal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 20));
				lightCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 1));
				bottomCal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 20));
				bottomCal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 8));
				star1Cal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 26));
				star1Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 20));
				star2Cal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 26));
				star2Cal.c_y = StaticValue.build(rect.top - PXUtils.dp2px(context, 20));
				break;
			case 5:
				lightCal.c_rotation = TimeLoop2.build(8, 360, .4f, .6f);
				bottomCal.c_rotation = StaticValue.build(180);

				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(50, 100) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);

				lightCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 14));
				lightCal.c_y = StaticValue.build(rect.bottom + PXUtils.dp2px(context, 2));

				bottomCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 14));
				bottomCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 16));

				star1Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 6));
				star1Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 20));

				star2Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 6));
				star2Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 20));
				break;
			case 6:
				lightCal.c_rotation = TimeLoop2.build(8, 360, .4f, .6f);
				bottomCal.c_rotation = StaticValue.build(180);

				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(100, 300) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);

				lightCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 84));
				lightCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 1));

				bottomCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 84));
				bottomCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 20));

				star1Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 76));
				star1Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 24));

				star2Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 76));
				star2Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 24));
				break;
			case 7:
				lightCal.c_rotation = TimeLoop2.build(8, 360, .4f, .6f);
				bottomCal.c_rotation = StaticValue.build(170);

				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(100, 200) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);

				lightCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 165));
				lightCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 2));

				bottomCal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 164));
				bottomCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 22));

				star1Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 158));
				star1Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 24));

				star2Cal.c_x = StaticValue.build(rect.left + PXUtils.dp2px(context, 158));
				star2Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 24));
				break;
			case 8:
			default:
				lightCal.c_rotation = TimeLoop2.build(8, 360, .3f, .7f);
				bottomCal.c_rotation = StaticValue.build(180);

				star1Cal.c_rotation = NeedleRotation.build(-MathCommonAlg.rangeRandom(50, 100) / 1000f);
				star2Cal.c_rotation = NeedleRotation.build(MathCommonAlg.rangeRandom(50, 200) / 1000f);

				lightCal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 50));
				lightCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 1));

				bottomCal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 50));
				bottomCal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 19));

				star1Cal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 58));
				star1Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 24));

				star2Cal.c_x = StaticValue.build(rect.right - PXUtils.dp2px(context, 58));
				star2Cal.c_y = StaticValue.build(rect.bottom - PXUtils.dp2px(context, 24));
				break;
		}
		lightCal.c_scale = StaticValue.build(1);
		lightCal.c_alpha = StaticValue.build(255);
		light.setCaculateCommonHandle(lightCal);
		bottomCal.c_scale = StaticValue.build(1);
		bottomCal.c_alpha = StaticValue.build(255);
		bottom.setCaculateCommonHandle(bottomCal);
		star1Cal.c_scale = StaticValue.build(1);
		star1Cal.c_alpha = StaticValue.build(255);
		star1.setCaculateCommonHandle(star1Cal);
		star2Cal.c_scale = StaticValue.build(1);
		star2Cal.c_alpha = StaticValue.build(255);
		star2.setCaculateCommonHandle(star2Cal);
	}

	/**
	 *  level7的左边钱袋
	 * @param bitmapShape
	 * @param rect
	 * @return: void
	 */

	public static void endowLevel7MoneyDai(BitmapShape bitmapShape, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final float left = rect.left - bitmapShape.getBitmapWidth() / 4;
		final float top = rect.top - bitmapShape.getBitmapHeight() / 6;
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { 1000, 50, 50, 50, 50, 50, 50, 50, 50 }, new float[] { 0, -5, 0, 10, -5, -5, 0, 10,
				-5 }, left, EnumConStant.MulEndMode.WITH_START_VALUE);
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 1000, 50, 50, 50, 50, 50, 50, 50, 50 },
				new float[] { 0, 5, -10, 0, 5, 5, -10, 0, 5 }, top, EnumConStant.MulEndMode.WITH_START_VALUE);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_alpha = StaticValue.build(255);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(5000);
	}

	/**
	 *  level7的钱币
	 * @param bitmapShape
	 * @param rect
	 * @return: void
	 */

	public static void endowLevel7MoneyBi(BitmapShape bitmapShape, Rect rect, int offsetTime) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final float left = rect.left;
		final float top = rect.top;
		final float right = rect.right;
		final float bottom = rect.bottom;
		final float width = rect.width();
		final float height = rect.height() / 2;
		float direction;
		if (MathCommonAlg.randomBool()) {
			caculateCommonHandle.c_x = DecayOpposite2.build(MathCommonAlg.randomFloat(-1, 0), left + width / 2);
			direction = MathCommonAlg.randomFloat(-1, 0);
		}
		else {
			direction = MathCommonAlg.randomFloat(-1, 1);
			caculateCommonHandle.c_x = Decay2.build(width / 2 * direction, MathCommonAlg.randomFloat(0.1f, 2), left + width / 2);
		}
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 300, 500, 1700 }, new float[] { -height * MathCommonAlg.randomFloat(0.3f, 0.8f),
				-height * MathCommonAlg.randomFloat(0.4f, 0.5f), -height * MathCommonAlg.randomFloat(0.2f, 0.6f) }, bottom - 10,
				EnumConStant.MulEndMode.WITH_END_CAL);
		caculateCommonHandle.c_rotation = MulSimpleCal.build(new int[] { 1000, 1000 }, new float[] { 180 * direction, 180 * direction }, 0,
				EnumConStant.MulEndMode.WITH_END_CAL);
		caculateCommonHandle.c_scale = StaticValue.build(1 * MathCommonAlg.randomFloat(0.8f, 1.5f));
		caculateCommonHandle.c_alpha = MulSimpleCal.build(new int[] { 1000, (int) (1000 * MathCommonAlg.randomFloat(0.5f, 1)) }, new float[] { 0,
				-255 }, 255, EnumConStant.MulEndMode.WITH_END_VALUE);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(4000);
		bitmapShape.setStartOffset(offsetTime);
		bitmapShape.setBlank(1000);

	}

	/**
	 *
	 * level7中向上抛的钱币
	 * @return: void
	 *
	 * 	//caculateCommonHandle.c_x = Decay2AWhile.build(rect.width() / 10*direction, MathCommonAlg.randomFloat(0, 2),
	//	MathCommonAlg.rangeRandom(rect.left, rect.right), last / 1000f);

	 */
	public static void endowLevel7BackgroundBiUp(BitmapShape bitmapShape, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final float left = rect.left + rect.width() * MathCommonAlg.randomFloat(.2f, .8f);
		final float top = rect.top;
		final float right = rect.right;
		final float bottom = rect.bottom;
		final float width = rect.width() / 30;
		final float height = rect.height();

		float direction;
//		if(MathCommonAlg.randomBool()){
//			caculateCommonHandle.c_x =DecayOpposite2.build(MathCommonAlg.randomFloat(-1,0),left);
//			direction=MathCommonAlg.randomFloat(-1,0);
//		}else{
		direction = MathCommonAlg.randomFloat(-1, 1);
		caculateCommonHandle.c_x = Decay2.build(width * direction, MathCommonAlg.randomFloat(0.1f, 2), left);
//		}
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 1000, 1500, 500 }, new float[] { -height * MathCommonAlg.randomFloat(0.2f, 0.4f),
				-height * MathCommonAlg.randomFloat(0.2f, 0.3f), -height * MathCommonAlg.randomFloat(0.1f, 0.2f) }, top + 10,
				EnumConStant.MulEndMode.WITH_END_CAL);
		caculateCommonHandle.c_rotation = MulSimpleCal.build(new int[] { 2000, 1000 }, new float[] { 180 * direction, 180 * direction }, 0,
				EnumConStant.MulEndMode.WITH_END_CAL);
		caculateCommonHandle.c_scale = MulSimpleCal.build(new int[] { 1000, 1000 }, new float[] { 0, -0.8f }, 0.8f,
				EnumConStant.MulEndMode.WITH_END_VALUE);
		caculateCommonHandle.c_alpha = MulSimpleCal.build(new int[] { 1000, (int) (2000 * MathCommonAlg.randomFloat(0.6f, 1)) }, new float[] { 0,
				-255 }, 255, EnumConStant.MulEndMode.WITH_END_VALUE);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(3000);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(1000, 3000));
		bitmapShape.setBlank(3000);
	}

	/**
	 *level7中向下抛的钱币
	 * @param bitmapShape
	 * @param rect
	 */
	public static void endowLevel7BackgroundBiDown(BitmapShape bitmapShape, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final float left = rect.left + rect.width() * MathCommonAlg.randomFloat(0.3f, .8f);
		final float top = rect.top;
		final float right = rect.right;
		final float bottom = rect.bottom;
		final float width = rect.width() / 30;
		final float height = rect.height();
		float direction;
		direction = MathCommonAlg.randomFloat(-1, 1);
		caculateCommonHandle.c_x = Decay2.build(width * direction, MathCommonAlg.randomFloat(0.1f, 2), left);
		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 1000, 1500, 500 }, new float[] { height * MathCommonAlg.randomFloat(0.2f, 0.4f),
				height * MathCommonAlg.randomFloat(0.2f, 0.3f), height * MathCommonAlg.randomFloat(0.1f, 0.2f) }, bottom - 10,
				EnumConStant.MulEndMode.WITH_END_CAL);
		caculateCommonHandle.c_rotation = MulSimpleCal.build(new int[] { 2000, 1000 }, new float[] { 180 * direction, 180 * direction }, 0,
				EnumConStant.MulEndMode.WITH_END_CAL);
		caculateCommonHandle.c_scale = MulSimpleCal.build(new int[] { 1000, 1000 }, new float[] { 0, -0.8f }, 0.8f,
				EnumConStant.MulEndMode.WITH_END_VALUE);
		caculateCommonHandle.c_alpha = MulSimpleCal.build(new int[] { 1000, (int) (2000 * MathCommonAlg.randomFloat(0.6f, 1)) }, new float[] { 0,
				-255 }, 255, EnumConStant.MulEndMode.WITH_END_VALUE);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(3000);
		bitmapShape.setStartOffset(MathCommonAlg.rangeRandom(1000, 3000));
		bitmapShape.setBlank(3000);
	}

	/**
	 * 初始化level3中的随机气泡
	 * @return: void
	*/
	public static void endowLevel3RandomBubble(PaintShape paintShape, Rect rect) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final float left = rect.left;
		final float top = rect.top;
		final float right = rect.right;
		final float bottom = rect.bottom;
		final float width = rect.width();
		final float height = rect.height();
		caculateCommonHandle.c_x = MulSimpleCal.build(new int[] { 1000, 1000 }, new float[] { width / 2, width / 2 }, left,
				EnumConStant.MulEndMode.WITH_END_VALUE);
//		caculateCommonHandle.c_y = MulSimpleCal.build(new int[] { 1000, 50, 50, 50, 50, 50, 50, 50, 50 },
//				new float[] { 0, 5, -10, 0, 5, 5, -10, 0, 5 }, top, EnumConStant.MulEndMode.WITH_END_VALUE);
		caculateCommonHandle.c_y = StaticValue.build(top + height / 2);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StaticValue.build(1);
		caculateCommonHandle.c_alpha = StaticValue.build(255);
		paintShape.setCaculateCommonHandle(caculateCommonHandle);
		paintShape.setPeriod(5000);
		paintShape.setBlank(3000);
		paintShape.setStartOffset(1000);
	}

	/**
	 * 初始化level5中的向上飘的圆形
	 * @return: void
	*/
	public static void endowLevel5BubbleUp(PaintShape paintShape, Rect rect, Context context) {
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final int left = rect.left;
		final int top = rect.top;
		final int right = rect.right;
		final int bottom = rect.bottom;
		final int width = rect.width();
		final int height = rect.height();
		caculateCommonHandle.c_x = CommonX.build(MathCommonAlg.rangeRandom(left, right), MathCommonAlg.rangeRandom(-10, 10) / 1000f);
		caculateCommonHandle.c_y = CommonX.build(bottom - PXUtils.dp2px(context, 2), MathCommonAlg.randomFloat(5, 20) / -1000);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StaticValue.build(MathCommonAlg.randomFloat(0.2f, 1));
		caculateCommonHandle.c_alpha = StaticValue.build(255);
		paintShape.setCaculateCommonHandle(caculateCommonHandle);
		paintShape.setPeriod(MathCommonAlg.rangeRandom(4000, 6000));
		paintShape.setBlank(MathCommonAlg.rangeRandom(0, 2000));
		paintShape.setStartOffset(MathCommonAlg.rangeRandom(0000, 3000));
	}

	/**
	 * 初始化level6中动态的小人
	 * @return: void
	*/
	@Deprecated
	public static void endowLevel6Person(BitmapShape boyBody, BitmapShape boyHead, BitmapShape boyLeg, BitmapShape girlBody, BitmapShape girlHead,
			BitmapShape girlLeg, Rect rect, Context context) {
		final int blank = 3000;
		final int period = 5000;
		final int left = rect.left;
		final int top = rect.top;
		final int right = rect.right;
		final int bottom = rect.bottom;
		final int width = rect.width();
		final int height = rect.height();

		//女孩的腿
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		caculateCommonHandle.c_x = StaticValue.build(left);
		caculateCommonHandle.c_y = StaticValue.build(bottom);
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StaticValue.build(1f);
		caculateCommonHandle.c_alpha = StaticValue.build(255);
		girlLeg.setCaculateCommonHandle(caculateCommonHandle);

//		paintShape.setCaculateCommonHandle(caculateCommonHandle);
//		paintShape.setPeriod(MathCommonAlg.rangeRandom(1000, 4000));
//		paintShape.setBlank(MathCommonAlg.rangeRandom(0, 2000));
//		paintShape.setStartOffset(MathCommonAlg.rangeRandom(0000, 3000));
	}

	/**
	 * level3中白色气泡
	 * @return: void
	*/
	public static void endowLevel3YellowCircle(CircleShape circleShape, Rect rect, Rect startRect, Context context) {
		final int left = rect.left;
		final int top = rect.top;
		final int right = rect.right;
		final int bottom = rect.bottom;
		final int width = rect.width();
		final int height = rect.height();
		final float valueF = MathCommonAlg.randomFloat(0, 1);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final boolean bool = MathCommonAlg.randomBool();
		final boolean b2 = MathCommonAlg.randomBool();
		final int one = b2 ? 1 : -1;
		if (bool) {
			caculateCommonHandle.c_x = Decay2.build(one * width * MathCommonAlg.randomFloat(0.05f, 0.3f), MathCommonAlg.randomFloat(0, 1.5f),
					startRect.left + width * valueF);
		}
		else {
			caculateCommonHandle.c_x = DecayOpposite2.build(one * MathCommonAlg.randomFloat(0.1f, 0.5f), startRect.left + width * valueF);
		}

		caculateCommonHandle.c_y = StandarCal.build(MathCommonAlg.rangeRandom(startRect.top, startRect.bottom),
				height * MathCommonAlg.randomFloat(0.4f, 1f));
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StandarCal.build(MathCommonAlg.randomFloat(1, 1.5f), -MathCommonAlg.randomFloat(0.2f, 2));
		caculateCommonHandle.c_alpha = StandarCal.build(255, -150);
		circleShape.setCaculateCommonHandle(caculateCommonHandle);
		circleShape.setPeriod(2000);
		circleShape.setBlank(1000);
		circleShape.setStartOffset((int) (1000 + 1000 * valueF));
	}

	/**
	 * level7中黄色粒子圆
	 * @param bitmapShape
	 * @param rect  背景的rect范围
	 * @param startRect 粒子初始位置的范围
	 * @param context
	 * @param direction
	 * @return: void
	*/
	public static void endowLevel7YellowCircle(Element bitmapShape, Rect rect, Rect startRect, Context context, boolean direction) {
		//背景的宽度和高度
		final int width = rect.width();
		final int height = rect.height();
		final float valueF = MathCommonAlg.randomFloat(0, 1);
		final boolean bool = MathCommonAlg.randomBool();
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		if (direction) {
			if (bool) {
				caculateCommonHandle.c_x = Decay2.build(-width / 10f * MathCommonAlg.randomFloat(1, 2), MathCommonAlg.randomFloat(0, 1.5f),
						startRect.left + width * valueF);//StaticValue.build(MathCommonAlg.randomFloat(rect.left,rect.right-circleShape.getRadius()));
			}
			else {
				caculateCommonHandle.c_x = DecayOpposite2.build(-MathCommonAlg.randomFloat(0.1f, 0.5f), startRect.left + width * valueF);
			}
		}
		else {
			if (bool) {
				caculateCommonHandle.c_x = Decay2.build(width / 10f * MathCommonAlg.randomFloat(1, 2), MathCommonAlg.randomFloat(0, 1.5f),
						startRect.right - width * valueF);//StaticValue.build(MathCommonAlg.randomFloat(rect.left,rect.right-circleShape.getRadius()));
			}
			else {
				caculateCommonHandle.c_x = DecayOpposite2.build(MathCommonAlg.randomFloat(0.1f, 0.5f), startRect.right - width * valueF);
			}
		}

		caculateCommonHandle.c_y = StandarCal.build(MathCommonAlg.rangeRandom(startRect.top, startRect.bottom),
				height * MathCommonAlg.randomFloat(0.2f, .8f));//StaticValue.build(MathCommonAlg.randomFloat(rect.top, rect.bottom-circleShape.getRadius()));
		caculateCommonHandle.c_rotation = StaticValue.build(0);
		caculateCommonHandle.c_scale = StandarCal.build(MathCommonAlg.randomFloat(0.8f, 1f), -.5f);//StaticValue.build(0.5f);//
		caculateCommonHandle.c_alpha = StaticValue.build(255);//StandarCal.build(255, -100);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(2000);
		bitmapShape.setBlank(1000);
		bitmapShape.setStartOffset((int) (1000 + 1000 * valueF));
	}

//	/**
//	 * 通用粒子前冲的模式测试.仅用于测试
//	 * @return: void
//	*/
//	public static void endowCommonFrontPoint(CircleShape circleShape, Rect rect, Context context) {
//		final int blank = 3000;
//		final int period = 5000;
//		final int left = rect.left;
//		final int top = rect.top;
//		final int right = rect.right;
//		final int bottom = rect.bottom;
//		final int width = rect.width();
//		final int height = rect.height();
//		final float valueF = MathCommonAlg.randomFloat(0, 1);
//		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
//		caculateCommonHandle.c_x = Decay2.build(-width / 6f * MathCommonAlg.randomFloat(1, 2), MathCommonAlg.randomFloat(0, 2), rect.top + width
//				* valueF);//StaticValue.build(MathCommonAlg.randomFloat(rect.left,rect.right-circleShape.getRadius()));
//		caculateCommonHandle.c_y = StandarCal
//				.build(rect.top + MathCommonAlg.rangeRandom(-10, 10), height / 2 * MathCommonAlg.randomFloat(0.5f, 3.5f));//StaticValue.build(MathCommonAlg.randomFloat(rect.top, rect.bottom-circleShape.getRadius()));
//		caculateCommonHandle.c_rotation = StaticValue.build(0);
//		caculateCommonHandle.c_scale = StandarCal.build(MathCommonAlg.randomFloat(1, 1.2f), -1f);
//		caculateCommonHandle.c_alpha = StandarCal.build(255, -100);
//		circleShape.setCaculateCommonHandle(caculateCommonHandle);
//		circleShape.setPeriod(2000);
//		circleShape.setBlank(1000);
//		circleShape.setStartOffset((int) (1000 + 1000 * valueF));
//	}

	/**
	 * level6中往前冲的玫瑰花, 但是不会旋转的玫瑰花
	 * @return: void
	*/
	public static void endowLevel6Rose(BitmapShape bitmapShape, Rect rect, Rect startRect, Context context) {
		final int left = rect.left;
		final int top = rect.top;
		final int right = rect.right;
		final int bottom = rect.bottom;
		final int width = rect.width();
		final int height = rect.height();
		final float valueF = MathCommonAlg.randomFloat(0, 1);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();
		final boolean bool = MathCommonAlg.randomBool();
		final boolean b2 = MathCommonAlg.randomBool();
		final int one = b2 ? 1 : -1;
		if (bool) {
			caculateCommonHandle.c_x = Decay2.build(one * width * MathCommonAlg.randomFloat(0.05f, 0.3f), MathCommonAlg.randomFloat(0, 1.5f),
					startRect.left + width * valueF);
		}
		else {
			caculateCommonHandle.c_x = DecayOpposite2.build(one * MathCommonAlg.randomFloat(0.1f, 0.5f), startRect.left + width * valueF);
		}

		caculateCommonHandle.c_y = StandarCal.build(MathCommonAlg.rangeRandom(startRect.top, startRect.bottom),
				-height * MathCommonAlg.randomFloat(0.4f, .6f));
		caculateCommonHandle.c_rotation = StandarCal.build(0, 60 * one);//StaticValue.build(0);
		caculateCommonHandle.c_scale = StandarCal.build(MathCommonAlg.randomFloat(0.2f, 1f), -MathCommonAlg.randomFloat(0.2f, 0.8f));
		caculateCommonHandle.c_alpha = StandarCal.build(255, -150);
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(2000);
		bitmapShape.setBlank(1000);
		bitmapShape.setStartOffset((int) (1500 + 1000 * valueF));
	}

	/**
	 * level6中平时玫瑰花
	*/
	public static void endowLevel6AlwaysRose(BitmapShape bitmapShape, Rect rect, Rect startRect, Context context, boolean direction) {
		final int left = rect.left;
		final int top = rect.top;
		final int right = rect.right;
		final int bottom = rect.bottom;
		final int width = rect.width();
		final int height = rect.height();
		final float valueF = MathCommonAlg.randomFloat(0, 1);
		CaculateCommonHandle caculateCommonHandle = new CaculateCommonHandle();

		final int one = MathCommonAlg.randomBool() ? 1 : -1;
		if (direction) {
			if (MathCommonAlg.randomBool()) {
				caculateCommonHandle.c_y = Decay2.build(-height * MathCommonAlg.randomFloat(0.2f, 0.4f), MathCommonAlg.randomFloat(0, 2f),
						MathCommonAlg.rangeRandom(startRect.top, startRect.bottom));
			}
			else {
				caculateCommonHandle.c_y = DecayOpposite2.build(-MathCommonAlg.randomFloat(0.1f, 0.6f),
						MathCommonAlg.rangeRandom(startRect.top, startRect.bottom));
			}
		}
		else {
			if (MathCommonAlg.randomBool()) {
				caculateCommonHandle.c_y = Decay2.build(height * MathCommonAlg.randomFloat(0.2f, 0.4f), MathCommonAlg.randomFloat(0, 2f),
						MathCommonAlg.rangeRandom(startRect.top, startRect.bottom));
			}
			else {
				caculateCommonHandle.c_y = DecayOpposite2.build(MathCommonAlg.randomFloat(0.1f, 0.6f),
						MathCommonAlg.rangeRandom(startRect.top, startRect.bottom));
			}
		}
		caculateCommonHandle.c_x = StandarCal.build(MathCommonAlg.rangeRandom(startRect.left, startRect.right),
				one * width * MathCommonAlg.randomFloat(.02f, .1f));// 
//		caculateCommonHandle.c_y =StaticValue.build(MathCommonAlg.rangeRandom(startRect.top, startRect.bottom));// StandarCal.build(MathCommonAlg.rangeRandom(startRect.top, startRect.bottom), height  * MathCommonAlg.randomFloat(0.4f,1f));
		caculateCommonHandle.c_rotation = StandarCal.build(0, 100);
		caculateCommonHandle.c_scale = StandarCal.build(MathCommonAlg.randomFloat(0.4f, .8f), -MathCommonAlg.randomFloat(0.1f, .2f));
//		caculateCommonHandle.c_scale = StaticValue.build(0.6f);//
		caculateCommonHandle.c_alpha = StandarCal.build(255, -MathCommonAlg.randomFloat(50, 100));
		bitmapShape.setCaculateCommonHandle(caculateCommonHandle);
		bitmapShape.setPeriod(MathCommonAlg.rangeRandom(2000, 3000));
		bitmapShape.setBlank(500);
		bitmapShape.setStartOffset((int) (500 + MathCommonAlg.rangeRandom(200, 1000)));
	}

}
