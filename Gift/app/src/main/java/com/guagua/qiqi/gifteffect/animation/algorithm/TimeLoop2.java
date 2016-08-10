package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 *
 * 设定轮训一次的时间。sin类型曲线-1-0-1循环
 * 这个对象修复了timeloop的设定错误，同时更加简化 
 * 和wave2很相似但是不一样
 * 
 */
public class TimeLoop2 extends CaculationModel {
	//设定几秒循环一次,单位是妙
	private float last;

	//内部使用参数
	private float r;
	private float m;

	//ratio比率
	private float ratio;

	//最小值
	private float beginValue;
	//最大值
	private float endValue;

	public TimeLoop2(float last, float ratio, float beginValue, float endValue) {
		super();
		this.last = last;
		this.ratio = ratio;
		this.beginValue = beginValue;
		this.endValue = endValue;
		m = (beginValue + endValue) / 2;
		r = endValue - m;
	}

	/**
	 * 设定轮训一次的时间。sin类型曲线-1-0-1循环
	 * sin((x/last-0.5/last)*MATH.2_PI)
	 * @param last 轮训的时间 单位时间是秒。
	 * @param ratio 系数，也就是1*ratio。
	 * @param start 轮训的最小值，
	 * @param end 轮训的最大值.
	 * @return: CaculationModel
	*/
	public static CaculationModel build(float last, float ratio, float start, float end) {
		return new TimeLoop2(last, ratio, start, end);
	}

	@Override
	public float caculate(int time) {
		return  (float) (ratio*(r*Math.sin((time/m_unit/last-0.25)*PI_2)+m));
	}
}
