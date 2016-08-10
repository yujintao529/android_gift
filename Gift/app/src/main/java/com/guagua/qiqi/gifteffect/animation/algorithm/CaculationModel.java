package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
public abstract class  CaculationModel {
    public static final float COMMON_UNIT=1000f;
    public float m_unit=COMMON_UNIT;//

    public static  final float HALF_VALUE=.5f;
    public abstract float caculate(int time);


    public static final double PI_2= Math.PI*2;


}
