package com.guagua.qiqi.gifteffect.animation.algorithm;


/*
 * 这个计算主要用过了几秒后执行操作。就是一个延迟执行的行为。 
 */


public class OffsetCal extends CaculationModel{

	//秒，多少秒后才执行。
	private float timeOffset;
	
	//开始前的默认值
	private float startValue;
	
	private CaculationModel realCaculationModel;
	protected OffsetCal(float timeOffset,float startValue,CaculationModel realCaculationModel){
		this.timeOffset=timeOffset;
		this.startValue=startValue;
		this.realCaculationModel=realCaculationModel;
	}
	
	@Override
	public final  float caculate(int time) {
		float value=startValue;
		if(time>=timeOffset*m_unit){
			value=realCaculationModel.caculate((int) (time-timeOffset*m_unit));
		}
		return value;
	}
	/**
	 * 
	 * @param timeOffset 过多长时间开始  单位是妙
	 * @param startValue 初始值。
	 * @param realCaculationModel 真正的计算
	 * @return: CaculationModel 
	 * 
	 */
	public static CaculationModel build(float timeOffset,float startValue,CaculationModel realCaculationModel){
		return new OffsetCal(timeOffset, startValue, realCaculationModel);
	}
	
}
