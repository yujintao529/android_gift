package com.guagua.qiqi.gifteffect.animation.algorithm;

/*************************************************************************************
* Module Name: StandarCal</br>
* File Name: <b>StandarCal.java</b></br>
* Description: y=kx+s</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class StandarCal extends CaculationModel {


    public float s_x;//初始值
    public float t_x;//速率 单位是秒
    public StandarCal(float s_x, float t_x) {
        this.s_x = s_x;
        this.t_x = t_x;
    }

    @Override
    public float caculate(int time) {
        return t_x/1000 * (time ) + s_x;
    }

    /**
     * @param s_x  初始值
     * @param t_x  速率 但是妙
     * @return: CaculationModel
    */
    public static CaculationModel build(float s_x, float t_x){
    	return new StandarCal(s_x, t_x);
    }
}
