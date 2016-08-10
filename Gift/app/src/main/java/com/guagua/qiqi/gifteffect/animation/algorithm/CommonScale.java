package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
@Deprecated
public class CommonScale extends CaculationModel {

    public float s_scale;
    public float t_scale;
    public float m_scale;
    public float b_scale;
    public float e_scale;
    public float r_scale;
    public CommonScale(float s_scale, float t_scale,float b_scale,float e_scale) {
        this.s_scale = s_scale;
        this.t_scale = t_scale;
        this.b_scale=b_scale;
        this.e_scale=e_scale;
        float sum=b_scale+e_scale;
        m_scale=sum/2;
        r_scale=e_scale-m_scale;
    }
    public CommonScale(float s_scale, float t_scale) {
        this(s_scale,t_scale,1f,1f);
    }
    public CommonScale() {
        this(0,0,1f,1f);
    }

    @Override
    public float caculate(int time) {
        return (float) ((r_scale*Math.sin(t_scale*(time/m_unit + s_scale)*PI_2))+m_scale);
    }
    
    @Override
    public String toString() {
        return "CommonScale{" +
                "s_scale=" + s_scale +
                ", t_scale=" + t_scale +
                ", m_scale=" + m_scale +
                '}';
    }
}
