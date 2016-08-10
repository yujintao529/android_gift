package com.guagua.qiqi.gifteffect.animation.algorithm;

import com.guagua.qiqi.gifteffect.AnimationException;

public class ParabolicCal extends CaculationModel{

	public float a;
	public float b;
	public float c;
	
	public ParabolicCal(){
		
	}
	
	@Override
	public float caculate(int time) {
		throw new AnimationException("parabolic不能直接调用...");
	}
	
	
	
	

}
