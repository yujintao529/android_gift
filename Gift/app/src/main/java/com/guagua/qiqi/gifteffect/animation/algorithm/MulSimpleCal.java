package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 *  根据多点的位置进行直线移动
 */
public class MulSimpleCal extends MulCal{

	protected float[] ks;
	

	protected MulSimpleCal(int[] times, float[] values, float startValue,EnumConStant.MulEndMode mulEndMode) {
		super(times, values, startValue,mulEndMode);
		ks=new float[times.length];
		initks(times, values, startValue);
	}
	/**
	 * tip，默认情况下，最后的速率会采用最后的的值。如果希望最后不在有变化，则可以设置1000，变换为0,就行了。但是因为最后的数值会有误差。所以如果希望为0可能不是0
	 * @param times 单位是豪秒
	 * @param values 变换值
	 * @param startValue
	 * @return: CaculationModel
	 */
	public static CaculationModel build(int[] times, float[] values, float startValue){
		return new MulSimpleCal(times,values,startValue, EnumConStant.MulEndMode.WITH_END_CAL);
	}
	/**
	 * tip，默认情况下，最后的速率会采用最后的的值。如果希望最后不在有变化，则可以设置1000，变换为0,就行了。但是因为最后的数值会有误差。所以如果希望为0可能不是0
	 * @param times 单位是豪秒
	 * @param values 变换值
	 * @param startValue
	 * @return: CaculationModel
	 */
	public static CaculationModel build(int[] times, float[] values, float startValue,EnumConStant.MulEndMode mulEndMode){
		return new MulSimpleCal(times,values,startValue,mulEndMode);
	}
	private void initks(int[] times, float[] values, float startValue){
		for(int i=0;i<times.length;i++){
			ks[i]=values[i]/times[i];
		}
	}
	@Override
	public float subCaculate(int time, int index, float lastValue) {
		return ks[index]*time+lastValue;
	}


}
