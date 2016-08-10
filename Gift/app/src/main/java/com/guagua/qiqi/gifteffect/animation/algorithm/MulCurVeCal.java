package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * 根据点的位置进行曲线移动
 */
public class MulCurVeCal extends MulCal{
	protected float[] ks;
	protected float[] ts;
	protected MulCurVeCal(int[] times, float[] values, float startValue) {
		super(times, values, startValue);
		ks=new float[times.length];
		ts=new float[times.length];
		initks(times, values, startValue);
	}
	
	//kx^2+t=
	private void initks(int[] times, float[] values, float startValue){
		for(int i=0;i<times.length;i++){
			
			final float value=values[i];
			final float time=times[i];
			if(i==0){
				ts[i]=startValue;
			}
//			final float value=values[i];
//			final float time=values[i];
//			if(i==0){
//				ks[i]=value-startValue/time;
//				continue;
//			}
//			final float lastValue=values[i-1];
//			final float lastTime=times[i-1];
//			ks[i]=(value-lastValue)/(time-lastTime);
//			ks[i]=values[i]/times[i];
		}
	}
	@Override
	public float subCaculate(int time, int index, float lastValue) {
		return ks[index]*time+lastValue;
	}

	public static CaculationModel build(int[] times, float[] values, float startValue){
		return new MulCurVeCal(times,values,startValue);
	}

}
