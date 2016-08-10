package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 * <p/>
 * x位置。默认线性函数。
 * y=kx+s
 */
public class CommonX extends CaculationModel {


    public float s_x;
    public float t_x;
    public CommonX(float s_x, float t_x) {
        this.s_x = s_x;
        this.t_x = t_x;
    }

    /**
     * 基础的单元为1000ms为1000像素
     * @param time
     * @return
     */
    @Override
    public float caculate(int time) {
        return t_x * (time ) + s_x;
    }

    public static CaculationModel build(float s_x, float t_x){
    	return new CommonX(s_x, t_x);
    }
    @Override
    public String toString() {
        return "CommonX{" +
                "s_x=" + s_x +
                ", t_x=" + t_x +
                '}';
    }
}
