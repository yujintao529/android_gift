package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行增长，速率逐渐变慢或者变快dratio主要是控制衰变的时间长度的曲线
 * rRatiox^dRatio + s;最小值
 */
@Deprecated
public class Decay extends CaculationModel {
    protected float dRatioValue;
    protected float rRatioValue;
    protected float s;
    protected Decay(EnumConStant.CommonRatio rRatio,float s,EnumConStant.DecayRatio dRatio){
        this.s=s;
        dRatioValue=dRatio.value();
        rRatioValue=rRatio.value();
    }
    protected Decay(float r,float s,EnumConStant.DecayRatio dRatio){
        this.s=s;
        dRatioValue=dRatio.value();
        rRatioValue=r;
    }
    @Override
    public float caculate(int time) {
        return (float) (rRatioValue*(Math.pow(time/m_unit,dRatioValue))+s);
    }
    public static CaculationModel build(float r,float s,EnumConStant.DecayRatio dRatio){
    	return new Decay(r, s, dRatio);
    }
}
