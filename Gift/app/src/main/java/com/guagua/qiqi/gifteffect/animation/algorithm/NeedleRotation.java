package com.guagua.qiqi.gifteffect.animation.algorithm;

/*************************************************************************************
* Module Name: NeedleRotation</br>
* File Name: <b>NeedleRotation.java</b></br>
* Description: 旋转</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class NeedleRotation extends CommonRotation {
    protected NeedleRotation(float t_rotation) {
        super(0f,t_rotation);
    }

    /**
     * 计算使用的单元毫秒
     *
     * @param time
     * @return
     */
    @Override
    public float caculate(int time) {
        return ((t_rotation * time % m_unit / m_unit))*360;
    }
    public static CaculationModel build(float t_r){
        return new NeedleRotation(t_r);
    }
}
