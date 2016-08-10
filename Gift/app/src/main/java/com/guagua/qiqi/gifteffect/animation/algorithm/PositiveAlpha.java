package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
public class PositiveAlpha extends  CommonAlpha {
    protected PositiveAlpha(float s_alpha, float t_alpha, float b_alpha, float e_alpha) {
        super(s_alpha, t_alpha, b_alpha, e_alpha);
    }

    @Override
    public float caculate(int time) {
        return Math.abs(super.caculate(time));
    }
    public static CaculationModel build(float s_alpha, float t_alpha, float b_alpha, float e_alpha){
        return new PositiveAlpha(s_alpha,t_alpha,b_alpha,e_alpha);
    }
}
