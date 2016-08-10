package com.guagua.qiqi.gifteffect.animation.algorithm;

/*************************************************************************************
* Module Name: NewWave</br>
* File Name: <b>NewWave.java</b></br>
* Description: 最简单的一个sinx函数</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class Wave2 extends CaculationModel{
	private float start;//初始值
	private float ratio;//sinx函数的比例
	private float b;//波动范围中的最小值
	private float e;//波动范围中的最大值
	private float t;//sinx中x的系数
	
	//通过计算得出
	private float r;//波动范围系数
	private float m;//波动范围的补
	private Wave2(float ratio,float start,float b,float e,float t){
		m=(b+e)/2;
		r=e-m;
		this.ratio=ratio;
		this.start=start;
		this.t=t;
	}

	/**
	 * (rsin((x-0.25/t)*6.28*t)+m))*ratio+start
	 * (rsin((6.28tx-1.57))+m)*ratio+start
	 * @param ratio  系数全用1000
	 * @param start  开始的位置.,也就是最小值
	 * @param b 波动范围的最小值
	 * @param e 波动范围的最大值
	 * @param t sinx的x系数.也就是该值越大，那么执行一次波动的时间越少。越小则执行一次波动的时间越长
	 * 			默认就是1秒执行一个周期,1/t
	 * 
	 * @return: CaculationModel
	*/
	public static CaculationModel build(float ratio,float start,float b,float e,float t){
		return new Wave2(ratio, start, b, e, t);
	}
	@Override
	public float caculate(int time) {
		return (float) (ratio*(r*Math.sin(PI_2*t*time/m_unit-Math.PI/2)+m)+start);
	}
}
