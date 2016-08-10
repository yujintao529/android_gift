package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by jintao on 2015/7/3.
 */
@Deprecated
public class CommonRotation extends CaculationModel {


    public float s_rotation;
    public float t_rotation;
    public float b_rotation;
    public float e_rotation;
    public float m_rotation;
    public float r_rotation;
    public CommonRotation(float s_rotation, float t_rotation,float b_rotation,float e_rotation) {
        this.s_rotation = s_rotation;
        this.t_rotation = t_rotation;
        this.b_rotation=b_rotation;
        this.e_rotation=e_rotation;
        float sum=b_rotation+e_rotation;
        m_rotation=sum/2;
        r_rotation=e_rotation-m_rotation;
    }
    public CommonRotation(float s_rotation,float t_rotation){
        this(s_rotation,t_rotation,-1,1);
    }
    //子类使用
    public CommonRotation(){

    }
    @Override
    public float caculate(int time) {
        return (float) ((r_rotation*Math.sin(t_rotation*((time/m_unit)+s_rotation)*PI_2)+m_rotation)* 360);
    }

    @Override
    public String toString() {
        return "CommonRotation{" +
                "s_rotation=" + s_rotation +
                ", t_rotation=" + t_rotation +
                ", b_rotation=" + b_rotation +
                ", e_rotation=" + e_rotation +
                ", m_rotation=" + m_rotation +
                ", r_rotation=" + r_rotation +
                '}';
    }
}
