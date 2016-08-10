package com.guagua.qiqi.gifteffect.animation;

import com.guagua.qiqi.gifteffect.animation.algorithm.CaculationModel;
import com.guagua.qiqi.gifteffect.animation.algorithm.CommonAlpha;
import com.guagua.qiqi.gifteffect.animation.algorithm.CommonRotation;
import com.guagua.qiqi.gifteffect.animation.algorithm.CommonScale;
import com.guagua.qiqi.gifteffect.animation.algorithm.CommonX;
import com.guagua.qiqi.gifteffect.animation.algorithm.CommonY;

/**
 * Created by jintao on 2015/7/3
 */
public class CaculateCommonHandle {
    public CaculationModel c_alpha;
    public CaculationModel c_scale;
    public CaculationModel c_scale_x;
    public CaculationModel c_scale_y;
    public CaculationModel c_rotation;
    public CaculationModel c_x;
    public CaculationModel c_y;
    public CaculationModel c_color;
    
    public void initComAlpha(float s_alpha, float t_alpha,float r_alpha, float m_alpha){
        c_alpha=new CommonAlpha(s_alpha,t_alpha,m_alpha,m_alpha);
    }
    public void initComAlpha(float s_alpha, float t_alpha){
        c_alpha=new CommonAlpha(s_alpha,t_alpha);
    }
    public void initComScale(float s_scale, float t_scale,float b_scale,float e_scale){
        c_scale=new CommonScale(s_scale,t_scale,b_scale,e_scale);
    }
    public void initComScale(float s_scale, float t_scale){
        c_scale=new CommonScale(s_scale,t_scale);
    }
    public void initComScale(){
        c_scale=new CommonScale();
    }
    public void initComRotation(float s_rotation, float t_rotation){
        c_rotation=new CommonRotation(s_rotation,t_rotation);
    }
    public void initComRotation(float s_rotation, float t_rotation,float b_rotation,float e_rotation){
        c_rotation=new CommonRotation(s_rotation,t_rotation,b_rotation,e_rotation);
    }
    public void initComX(float s_x, float t_x){
        c_x=new CommonX(s_x,t_x);
    }
    public void initComY(float s_y, float t_y){
        c_y=new CommonY(s_y,t_y);
    }

    @Override
    public String toString() {
        return "CaculateCommonHandle{" +
                "c_alpha=" + c_alpha +
                ", c_scale=" + c_scale +
                ", c_rotation=" + c_rotation +
                ", c_x=" + c_x +
                ", c_y=" + c_y +
                '}';
    }

}
