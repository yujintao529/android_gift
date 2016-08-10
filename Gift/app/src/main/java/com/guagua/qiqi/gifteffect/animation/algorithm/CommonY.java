package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 * <p/>
 * x位置。默认线性函数。
 * y=kx+s
 */
public class CommonY extends CaculationModel {


    public float s_y;
    public float t_y;

    public CommonY(float s_y, float t_y) {
        this.s_y = s_y;
        this.t_y = t_y;
    }

    /**
     * 基础的单元为1000ms为1000像素
     * @param time
     * @return
     */
    @Override
    public float caculate(int time) {
        return t_y * (time) + s_y;
    }

    @Override
    public String toString() {
        return "CommonY{" +
                "s_y=" + s_y +
                ", t_y=" + t_y +
                '}';
    }
}
