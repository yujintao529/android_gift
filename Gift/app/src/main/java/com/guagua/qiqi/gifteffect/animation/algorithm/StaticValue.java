package com.guagua.qiqi.gifteffect.animation.algorithm;

public class StaticValue extends CaculationModel{
	
	private float value;
	private StaticValue(float value){
		this.value=value;
	}
	@Override
	public float caculate(int time) {
		return value;
	}
	public static CaculationModel build(float value){
		return new StaticValue(value);
	}

}
