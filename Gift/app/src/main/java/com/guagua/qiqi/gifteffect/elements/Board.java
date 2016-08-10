package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.guagua.qiqi.gifteffect.util.BitmapUtils;

/**
 * Created by jintao on 2015/7/28.
 */
public class Board  {
    int mWidth;
    int mHeight;
    Bitmap mRealBoard;
    private Matrix mMatrix;
    private Paint clear;
    Canvas mCanvas;
    private int mBGColor=0x00000000;
    public Board(int width,int height){
        this.mWidth=width;
        this.mHeight=height;
        mRealBoard=BitmapUtils.craeteBitmap(mWidth,mHeight);
        mCanvas=new Canvas(mRealBoard);
        clear=new Paint();
        clear.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }
    public void setBGColor(int color){
    	mBGColor=color;
    }
    public void clear(){
        mCanvas.drawPaint(clear);
        mCanvas.drawColor(mBGColor);
    }
}
