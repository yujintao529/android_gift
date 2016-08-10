package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/6.
 */
public class Common extends CaculationModel {
    private float ratio;
    public float s;
    public float t;
    public float m;
    public float b;
    public float e;
    public float r;

    public Common(EnumConStant.CommonRatio ratio, float s, float t, float b, float e) {
        this.ratio = ratio.value();
        this.s = s;
        this.t = t;
        this.e=e;
        this.b = b;
        m=(b+e)/2;
        r=e-m;
    }
    public Common(float ratio, float s, float t, float b, float e) {
        this.ratio = ratio;
        this.s = s;
        this.t = t;
        this.e=e;
        this.b = b;
        m=(b+e)/2;
        r=e-m;
    }
    public static CaculationModel build(float ratio, float s, float t, float b, float e){
        return new Common(ratio,s,t,b,e);
    }
    @Override
    public float caculate(int time) {
        return (float) ((r*Math.sin(t*(time/m_unit + s)*PI_2))+m)*ratio;
    }

    @Override
    public String toString() {
        return "Common{" +
                "ratio=" + ratio +
                ", s=" + s +
                ", t=" + t +
                ", m=" + m +
                ", b=" + b +
                ", e=" + e +
                ", r=" + r +
                '}';
    }
}
