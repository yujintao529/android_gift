package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
public class PositiveRotation extends CommonRotation {
    protected PositiveRotation(float s_rotation, float t_rotation, float b_rotation, float e_rotation) {
        super(s_rotation, t_rotation, b_rotation, e_rotation);
    }

    @Override
    public float caculate(int time) {
        return Math.abs(super.caculate(time));
    }
    public static CaculationModel build(float s_rotation, float t_rotation, float b_rotation, float e_rotation){
        return new PositiveRotation(s_rotation,t_rotation,b_rotation,e_rotation);
    }
}
