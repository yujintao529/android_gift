package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
public class NoNegetiveAlpha extends CommonAlpha{
    public NoNegetiveAlpha(float s_alpha, float t_alpha, float b_alpha, float e_alpha) {
        super(s_alpha, t_alpha, b_alpha, e_alpha);
    }

    @Override
    public float caculate(int time) {
        float result=super.caculate(time);
        return result<=0?0:result;
    }
}
