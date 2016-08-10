package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行增长，速率逐渐变慢或者变快dratio主要是控制衰变的时间长度的曲线
 * ratioX^index + s;最小值
 *
 */
public class Decay2AWhile extends CaculationModel {
    protected float ratio;
    protected float index;
    protected float startingValue;
    protected float last;
    protected Decay2AWhile(float ratio, float index, float startingValue,float last) {
        this.ratio = ratio;
        this.index = index;
        this.startingValue = startingValue;
        this.last=last;
    }

    protected Decay2AWhile(float startingValue,float last) {
        this.startingValue = startingValue;
        this.ratio=1000;
        this.index=2;
        this.last=last;
    }

    @Override
    public float caculate(int time) {
        return (float) (ratio*Math.pow(time%(last*m_unit)/m_unit,index)+ startingValue);
    }

    /**
     *
     * @param ratio 系数
     * @param index 指数 指数如果大于1，那么就是增长速率越来越快，如果大于0小于1，那么就是增长速率越来越慢
     * @param startingValue 初始值
     * @param last 执行多长时间进行循环 单位为妙( 单位是妙！！！)
     * 有几个大概的衰变速率1秒内增加肯定是ratio的大小。因为目前time没有参数。
     * 
     *
     * @return
     */
    public static CaculationModel build(float ratio,float index,float startingValue,float last){
    	return new Decay2AWhile(ratio, index, startingValue,last);
    }
    public static CaculationModel build(float startingValue,float last){
        return new Decay2AWhile(startingValue,last);
    }
}
