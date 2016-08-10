package com.guagua.qiqi.gifteffect.animation.algorithm;

import com.guagua.qiqi.gifteffect.AnimationException;
import com.guagua.qiqi.gifteffect.animation.algorithm.EnumConStant.MulEndMode;

/*************************************************************************************
 * Module Name: MulCal</br>
 * File Name: <b>MulCal.java</b></br>
 * Description: 多个计算函授需要的。</br>
 * Author: 郁金涛</br>
 * 版权 2008-2015，金华长风信息技术有限公司</br>
 * 所有版权保护
 * 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
 * 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
 ***************************************************************************************/
public abstract class MulCal extends CaculationModel{
	//两个数组长度必须相等
	protected int[] times;//单位是毫秒
	protected float[] values;//对应毫秒内数值的变化
	private int index;//当前执行的阶段
	private float startValue;//开始值
	private float lastValue;//上一次计算值
	private int saveTime;//用于纪录时间
	private EnumConStant.MulEndMode mulEndMode;
	private final float permanStartValue;//用于存储初始值

	protected MulCal(int[] times, float values[], float startValue,EnumConStant.MulEndMode mulEndMode){
		this.times=times;
		this.values=values;
		if(times.length!=values.length){
			throw new AnimationException("mulcal times and values length must equal");
		}
		permanStartValue=this.startValue=startValue;
		lastValue=startValue;
		this.mulEndMode=mulEndMode;
	}
	protected MulCal(int[] times, float values[], float startValue){
		this.times=times;
		this.values=values;
		if(times.length!=values.length){
			throw new AnimationException("mulcal times and values length must equal");
		}
		permanStartValue=this.startValue=startValue;
		lastValue=startValue;
		this.mulEndMode= EnumConStant.MulEndMode.WITH_END_CAL;
	}
	/**
	 *
	 * @param time 每个阶段执行的准确时间时间。多个阶段的话，那么每个阶段应该都是0开始走的，但是由于轮训时间的误差，所以time值可能不是0
	 * @param index  阶段序号
	 * @param lastValue 此阶段开始的值
	 * @return
	 */
	public abstract float subCaculate(int time, int index,float lastValue);

	//仅仅用于判定时间是否重新记时了
	private int record=0;
	private boolean isOver;//所有阶段是否全部完成
	/**
	 * 参数重新初始化。
	 * @return: void
	 */
	protected void reset(){
		startValue=permanStartValue;
		index=0;
		saveTime=0;
		isOver=false;
	}
	//为了解决时间单元造成的参数变化，需要进行重设.一旦发现时间重新走了，进行重设
	@Override
	public float caculate(int time) {
		if(record>time){
			reset();
		}
		record=time;
		if(!isOver&&(time-saveTime)>times[index]){
			index++;
			startValue=lastValue;
			saveTime=time;
			isOver=(index==times.length);
		}
		if(!isOver||mulEndMode== EnumConStant.MulEndMode.WITH_END_CAL) {
			lastValue = subCaculate(time - saveTime,isOver?index-1:index, startValue);
		}else if(mulEndMode==MulEndMode.WITH_START_VALUE){
			lastValue=permanStartValue;
		}
		return lastValue;
	}


}
