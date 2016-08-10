package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
@Deprecated
public class CommonAlpha extends CaculationModel {


    public float s_alpha;
    public float t_alpha;
    public float b_alpha;//开始范围
    public float e_alpha;//终止范围
    //下面两个值需要经过简单计算获得

    public float r_alpha;
    public float m_alpha;

    public CommonAlpha(float s_alpha, float t_alpha,float b_alpha, float e_alpha) {
        this.s_alpha = s_alpha;
        this.t_alpha = t_alpha;
        this.b_alpha = b_alpha;
        this.e_alpha = e_alpha;
        float sum=b_alpha+e_alpha;
        m_alpha=sum/2;
        r_alpha=e_alpha-m_alpha;
    }
    public CommonAlpha(float s_alpha, float t_alpha) {
        this(s_alpha,t_alpha,-1,1);

    }


    @Override
    public float caculate(int time) {
        return (float) (((r_alpha*Math.sin(t_alpha*(time/m_unit* PI_2) + s_alpha))+m_alpha) * 255);
    }


    @Override
    public String toString() {
        return "CommonAlpha{" +
                "s_alpha=" + s_alpha +
                ", t_alpha=" + t_alpha +
                ", b_alpha=" + b_alpha +
                ", e_alpha=" + e_alpha +
                ", r_alpha=" + r_alpha +
                ", m_alpha=" + m_alpha +
                '}';
    }
}
