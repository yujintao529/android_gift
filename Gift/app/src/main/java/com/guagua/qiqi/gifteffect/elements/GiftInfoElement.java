package com.guagua.qiqi.gifteffect.elements;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;

import com.guagua.qiqi.gifteffect.SceneInfo;
import com.guagua.qiqi.gifteffect.util.BitmapUtils;
import com.guagua.qiqi.gifteffect.util.CommonUtils;
import com.guagua.qiqi.gifteffect.util.Logger;
import com.guagua.qiqi.gifteffect.util.PXUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * **********************************************************************************
 * Module Name: GiftInfoElement</br>
 * File Name: <b>GiftInfoElement.java</b></br>
 * Description: </br>
 * Author: 郁金涛</br>
 * 版权 2008-2015，金华长风信息技术有限公司</br>
 * 所有版权保护
 * 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
 * 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
 * *************************************************************************************
 */
public class GiftInfoElement extends Element {

    public static final byte ROLL_MODE_ONE = 1;
    public static final byte ROLL_MODE_TWO = 2;


    public static final String SONG = "  送  ";
    public static final String UNIT = "  个  ";
    public static final int INFO_TEXT_SIZE = 30;
    public static final int DENSITY_320 = 320;
    public static final int GIFT_BITMAP_SZIE = 25;
    private Rect mBGRect;
    private SceneInfo info;
    private Rect textBounds;

    private Rect unitBounds;
    private Paint.FontMetrics fontMetrics;
    private float y;
    private float ux;

    //描边的paintffff22字体，bb5301描边
    //真正字体paint
    private Paint realPaint;
    //描边字体paint
    private Paint eagePaint;
    //单位字体paint
    private Paint unitPaint;
    //单位字体描边paint
    private Paint unitEagePaint;


    //数字的滚动区域
    private RectF paintRect;
    private RectF numberRect;
    //图片
    private Bitmap[] bitmapsNumber;

    //数字的高度和宽度
    private int numberWidth;
    private int numberheight;

    GunNumberGroup[] gunNumberGroups;


    //当前用户送的礼物个数的图片的bitmap容器
    List<Bitmap> mNumberBitmaps;

    //显示的用户名字
    private String mUserName;
    //礼物的bitmap
    private Bitmap mGiftBitmap;
    private Rect mGiftBitmapRect;


    public GiftInfoElement(IScene iScene, final SceneInfo info, Rect deprated) {
        super(iScene);
        setEnableMatrix(false);
        mUserName = CommonUtils.getSubStringByLimit(info.sender, 10);
        final int size = CommonUtils.getDensityFontSize((Activity) iScene.mContext, INFO_TEXT_SIZE);
        //一个真正字体的paint.一个是描边的paint
        realPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        realPaint.setColor(Color.RED);
        realPaint.setTextAlign(Paint.Align.RIGHT);
        realPaint.setTextSize(size);
        realPaint.setStyle(Style.FILL_AND_STROKE);
        eagePaint = new Paint(realPaint);
        eagePaint.setStrokeWidth(2); // 描边宽度
        eagePaint.setStyle(Style.FILL_AND_STROKE); //描边种类
        eagePaint.setFakeBoldText(true); // 外层text采用粗体
        eagePaint.setShadowLayer(1, 0, 0, 0); //字体的阴影效果，可以忽略
        eagePaint.setColor(Color.WHITE);

        this.info = info;
        textBounds = new Rect();
        mBGRect = iScene.getBGRect();
        fontMetrics = new Paint.FontMetrics();
        realPaint.getTextBounds(mUserName + SONG, 0, mUserName.length(), textBounds);
        realPaint.getFontMetrics(fontMetrics);
        y = (mBGRect.height() + fontMetrics.top - fontMetrics.bottom) / 2 - fontMetrics.top;

        //单位文字的属性
        unitPaint = new Paint(realPaint);
        unitPaint.setTextAlign(Align.LEFT);
        unitEagePaint = new Paint(eagePaint);
        unitEagePaint.setTextAlign(Align.LEFT);
        unitBounds = new Rect();
        unitPaint.getTextBounds(UNIT, 0, UNIT.length(), unitBounds);
        ux = realPaint.measureText(UNIT);


        //礼物bitmap属性
        mGiftBitmapRect = new Rect();
        mGiftBitmap = BitmapUtils.createBitmap(info.getGiftBitmap(), PXUtils.dp2px(mIScene.mContext, GIFT_BITMAP_SZIE), PXUtils.dp2px(mIScene.mContext, GIFT_BITMAP_SZIE));
        Logger.d("mgiftbitmap height 1 " + mGiftBitmap.getHeight() + " width " + mGiftBitmap.getWidth());
        mGiftBitmapRect.left = 0;
        mGiftBitmapRect.top = (mBGRect.height() - mGiftBitmap.getHeight()) / 2;
        mGiftBitmapRect.right = mGiftBitmap.getWidth();
        mGiftBitmapRect.bottom = mGiftBitmapRect.top + mGiftBitmap.getHeight();
//		final int height = mGiftBitmap.getHeight();
//		bitmapTop = (mBGRect.height() - height) / 2;
//		Logger.d("mgiftbitmap height 2 "+mGiftBitmap.getHeight()+" width "+mGiftBitmap.getWidth());
        //滚动数字的数据
        //初始化10个bitmap数字图片


        bitmapsNumber = new Bitmap[10];
        for (int i = 0; i < 10; i++) {
            bitmapsNumber[i] = BitmapUtils.decodeBitmap(iScene.mContext,
                    iScene.mContext.getResources().getIdentifier("number_" + i, "drawable", iScene.mContext.getPackageName()));
            numberheight = bitmapsNumber[i].getHeight();
            numberWidth = bitmapsNumber[i].getWidth();
        }
        numberRect = new RectF();
        mNumberBitmaps = new ArrayList<Bitmap>(5);
        int temp = info.num;
        do {
            int number = temp % 10;
            mNumberBitmaps.add(bitmapsNumber[number]);
        }
        while ((temp /= 10) > 0);
        numberRect.left = 0;
        numberRect.top = (mBGRect.height() - numberheight) / 2;
        numberRect.right = numberWidth * mNumberBitmaps.size();
        numberRect.bottom = numberRect.top + numberheight;
        paintRect = new RectF(numberRect);
        paintRect.bottom = paintRect.top + numberheight * 3;
        //滚动计算公式
        /** 会造成误差*/
        gunNumberGroups = new GunNumberGroup[4];
        gunNumberGroups[0] = new GunNumberGroup(0, numberRect.top, SpeicalCalModel.QIANWEI);
        gunNumberGroups[1] = new GunNumberGroup(numberWidth, numberRect.top, SpeicalCalModel.BAIWEI);
        gunNumberGroups[2] = new GunNumberGroup(numberWidth * 2, numberRect.top, SpeicalCalModel.SHIWEI);
        gunNumberGroups[3] = new GunNumberGroup(numberWidth * 3, numberRect.top, SpeicalCalModel.GEWEI);
        /**end*/
    }

    private Paint testPaint = new Paint();

    @Override
    protected void draw(Canvas canvas, Matrix matrix, Paint paint, int timeDifference) {
        canvas.save();
        //以bg背景为坐标原点
        canvas.translate(mBGRect.left, mBGRect.top);
        //首先描绘送礼人信息
        canvas.drawText(mUserName + SONG, mBGRect.width() / 2, y, eagePaint);
        canvas.drawText(mUserName + SONG, mBGRect.width() / 2, y, realPaint);
        //移动坐标原点为背景x坐标一半的位置。描绘滚动数组
        canvas.translate(mBGRect.width() / 2, 0);
        //滚动数字
        canvas.save();
        /**这种方法会造成误差*/
        canvas.clipRect(numberRect);
        for (int i = 0; i < gunNumberGroups.length; i++) {
            gunNumberGroups[i].doDraw(canvas, testPaint, timeDifference);
        }
        /**end**/
//		for (int i = mNumberBitmaps.size() - 1, j = 0, size = mNumberBitmaps.size(); i >= 0; i--) {
//			canvas.drawBitmap(mNumberBitmaps.get(i), numberRect.left + j++ * numberWidth, numberRect.top, testPaint);
//		}
        canvas.restore();
        canvas.translate(numberRect.width(), 0);
        //单位
        canvas.drawText(UNIT, 0, y, unitEagePaint);
        canvas.drawText(UNIT, 0, y, unitPaint);
        //礼物
        canvas.translate(ux, 0);
        canvas.drawBitmap(mGiftBitmap, 0, mGiftBitmapRect.top, null);
        canvas.restore();
    }


    @Override
    protected void destroy() {
        super.destroy();
        BitmapUtils.destroy(mGiftBitmap);
        for (int i = 0; i < 10; i++) {

        }
    }


    /**
     * 滚动数字封装对象.
     */
    private final class GunNumber {
        public float top;
        public int index;
        public int standard;

        public GunNumber(int index, float top, int standard) {
            this.top = index * numberheight + top;
            this.index = index;
            this.standard = standard;
        }

        public void doDraw(Canvas canvas, Paint paint, float left, float value) {
            top += (value * -1);
            if (top <= numberheight * -1) {
                top = standard + top;
                index += 3;
                index %= 10;
            }
            canvas.drawBitmap(bitmapsNumber[index], left, top, paint);
        }
    }

    /**
     * 一列数据，控制速度，显示的范围，三行
     */
    private class GunNumberGroup {
        GunNumber[] gunNumbers = new GunNumber[3];
        float left;
        float top;
        //滚动计算的公式
//        CaculationModel mCalModel;
        Caculation caculation;
        float lastValue;

        int lastNumber = 9;

        public GunNumberGroup(float left, float top, int type) {
            gunNumbers[0] = new GunNumber(0, top, numberheight * 3);
            gunNumbers[1] = new GunNumber(1, top, numberheight * 3);
            gunNumbers[2] = new GunNumber(2, top, numberheight * 3);
            this.left = left;
            this.top = top;
            caculation = new SpeicalCalModel(type, 2 - type * 0.2f);
        }

        public void setLastNumber(int lastNumber) {
            this.lastNumber = lastNumber;
        }


        public void doDraw(Canvas canvas, Paint paint, int timeDifference) {
            final float value = caculation.caculate(timeDifference);
            for (int i = 0, size = gunNumbers.length; i < size; i++) {
                gunNumbers[i].doDraw(canvas, paint, left, value - lastValue);
            }
            lastValue = value;
        }

    }


    public class SpeicalCalModel implements Caculation {

        public static final int GEWEI = 0;
        public static final int SHIWEI = 1;
        public static final int BAIWEI = 2;
        public static final int QIANWEI = 3;


        private float rat;
        private float count;

        public SpeicalCalModel(int type, float time) {
            switch (type) {
                case GEWEI:
                    count = 39 * numberheight;
                    rat = count / time / 1000;
                    break;
                case SHIWEI:
                    count = 29 * numberheight;
                    rat = count / time / 1000;
                    break;
                case BAIWEI:
                    count = 19 * numberheight;
                    rat = count / time / 1000;
                    break;
                case QIANWEI:
                    count = 9 * numberheight;
                    rat = count / time / 1000;
                    break;

            }

        }


        @Override
        public float caculate(int time) {
            final float value = rat * time;
            if (value >= count) {
                return count;
            }
            return value;
        }
    }


    public interface Caculation {
        float caculate(int time);
    }


}
