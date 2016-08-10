package com.guagua.qiqi.gifteffect.animation.algorithm;

import android.graphics.Rect;

import com.guagua.qiqi.gifteffect.AnimationException;

/**
 * Created by jintao on 2015/7/6.
 * 
 *
 *
 */
public abstract  class CollideBox extends CaculationModel{


    protected static final int CATEGORY_X=1;
    protected static final int CATEGORY_Y=2;
    protected Rect mRect;
    protected int mWidth;
    protected int mHeight;
    private CaculationModel mModelX;
    private CaculationModel mModelY;
    private float mXCollideTime;
    private float mYCollideTime;

    //默认起始点
    protected float mStartX;
    protected float mStartY;

    protected CollideBox(Rect rect){

        mRect=rect;
        mStartY=MathCommonAlg.rangeRandom(rect.top+1,rect.bottom);//保证top大于0
        mStartX=MathCommonAlg.rangeRandom(rect.left+1,rect.right);
        mWidth=mRect.width();
        mHeight=mRect.height();
    }


    protected abstract void refresh(int category);
    protected abstract float caculateX(float timeDifference,float realTime);
    protected abstract float caculateY(float timeDifference,float realTime);















    public CaculationModel newModleX(){
        if(mModelX==null){
            mModelX=new CollideProxyX();
        }
        return mModelX;
    }

    public CaculationModel newModleY(){
        if(mModelY==null){
            mModelY=new CollideProxyY();
        }
        return mModelY;
    }













    @Override
    public float caculate(int time) {
        throw new AnimationException("CollideBox can not direct use");
    }










    /**
     * ....god
     */
    private class CollideProxyX extends CaculationModel{

        @Override
        public float caculate(int time) {

            final float next= caculateX(time-mXCollideTime,time);
            if(next <=mRect.left){
                refresh(CATEGORY_X);
                mXCollideTime=time;
                mStartX=mRect.left;
            }else if(next>=mRect.right){
                refresh(CATEGORY_X);
                mXCollideTime=time;
                mStartX=mRect.right;
            }
            return next;
        }
    }
    private class CollideProxyY extends CaculationModel{

        @Override
        public float caculate(int time) {
            final float next= caculateY(time-mYCollideTime,time);
            if(next <=mRect.top){
                refresh(CATEGORY_Y);
                mYCollideTime=time;
                mStartY=mRect.top;
            }else if(next>=mRect.bottom){
                refresh(CATEGORY_Y);
                mYCollideTime=time;
                mStartY=mRect.bottom;
            }
            return next;
        }
    }
}
