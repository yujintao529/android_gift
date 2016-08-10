package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行增长，速率逐渐变慢或者变快dratio主要是控制衰变的时间长度的曲线
 * ratioX^index + s;最小值
 */
public class Decay2 extends CaculationModel {
    protected float ratio;
    protected float index;
    protected float startingValue;

    protected Decay2(float ratio, float index, float startingValue) {
        this.ratio = ratio;
        this.index = index;
        this.startingValue = startingValue;
    }

    protected Decay2(float startingValue) {
        this.startingValue = startingValue;
        this.ratio=1000;
        this.index=2;
    }

    @Override
    public float caculate(int time) {
        return (float) (ratio*Math.pow(time/m_unit,index)+ startingValue);
    }

    /**
     *
     * @param ratio 系数
     * @param index 
     *      指数 指数如果大于1，那么就是增长速率越来越快，如果大于0小于1，那么就是增长速率越来越慢
     * 		无论index值为多少，1的指数肯定还是1，所以1秒内的变化肯定ratio也就是系数
     * 		 如果小于0大于1，那么index越大，前期变换的变化的越快。
     * 		如果大于1，那么index越大，前期变换的变化越慢,后期就飞快了
     * @param startingValue 初始值
     */
    public static CaculationModel build(float ratio,float index,float startingValue){
    	return new Decay2(ratio, index, startingValue);
    }
    public static CaculationModel build(float startingValue){
        return new Decay2(startingValue);
    }
}
