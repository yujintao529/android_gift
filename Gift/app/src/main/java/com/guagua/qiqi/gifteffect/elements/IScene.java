package com.guagua.qiqi.gifteffect.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.guagua.qiqi.gifteffect.SceneInfo;

/**
 * Created by jintao on 2015/7/2.
 * 场景几类
 */
public abstract class IScene implements ISignt{
    public static final String TAG = IScene.class.getSimpleName();
    public static final int DEFAULT_SHOW_LAST_TIME=6000;
    public static final int LAST_FOREVER=-1;//一直播放

    public static final int EMPTY=1;
    public static final int BEGIN=2;
    public static final int SHOWING=3;
    public static final int END=4;
    public static final int DEAD=-1;


    private long mStartTime;

    private int mLastTime;//场景显示时间
    //场景里面的元素
    Element[] mElements;
    //当前元素个数
    private int mIndex;
    //元素数量
    private int maxNumber;

    private int mStatus;
    //Context
    protected Context mContext;

    //scene的宽度和高度
    protected int mWidth;
    protected int mHeight;
    
    
    //每个场景背景图片的rect，小于或者等于场景的大小
    protected Rect mBGRect;
    
    //当前送礼信息
    protected SceneInfo sceneInfo;
    
    public IScene(Context context, int number, int width, int height) {
        mElements = new Element[number];
        maxNumber = number;
        mIndex = 0;
        mContext = context;
        this.mWidth = width;
        this.mHeight = height;
        mStatus=EMPTY;
        mBGRect=new Rect(0, 0, width, height);
        setmLastTime(DEFAULT_SHOW_LAST_TIME);
    }
    
    
    /**
     * 绘图前需要调用这个方法进行bg范围规划
     * @param rect
     * @return: void
    */
    protected void setBGRect(Rect rect){
    	mBGRect.set(rect);
    }
    protected final Rect getBGRect(){
    	return new Rect(mBGRect);
    }

    @Override
    public boolean isPlayEnd() {
         return mStatus == END;
    }

    @Override
    public boolean readyForPlay() {
        mStartTime = System.currentTimeMillis();
        mStatus=BEGIN;
        onBeforeShow();
        return true;
    }

    public IScene(Context context, int width, int height) {
       this(context,width,height,50);
    }

    @Override
    public void playEnd() {
        mStatus=END;
    }


    public int getlastTime(){
        return mLastTime;
    }
    public final void play(Canvas canvas) {
        int i = 0;
        Element element;
        final int timeSpan = (int) (System.currentTimeMillis() - mStartTime);
//        Logger.d("scene draw time "+timeSpan);
        for (; ; ) {
            if (i < mIndex) {
                element = mElements[i++];
                element.doDraw(canvas, timeSpan);
            } else {
                break;
            }
        }
        if(timeSpan>=mLastTime&&mLastTime!=LAST_FOREVER){
            mStatus=END;
            onAfterShow();
        }
    }

    public void setmLastTime(int mLastTime) {
        this.mLastTime = mLastTime;
    }

    
    protected void onBeforeShow(){
    }
    protected void onAfterShow(){
    	
    }


    public void destroy(){
    	for(int index=0;index<mIndex;index++){
    		mElements[index].destroy();
    	}
    }

    public void setSceneInfo(SceneInfo sceneInfo){
    	this.sceneInfo=sceneInfo;
    }

    protected final void addShape(Element shape) {
        if(mIndex>=maxNumber){
            Element[] now=new Element[maxNumber*2];
            System.arraycopy(mElements,0,now,0,maxNumber);
            mElements=now;
            maxNumber=maxNumber*2;
        }
        mElements[mIndex++] = shape;
    }


	@Override
	public String toString() {
		return "IScene [sceneInfo=" + sceneInfo + "]";
	}


    
}
