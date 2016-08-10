package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 *  根据多点的位置进行直线移动
 */
public class MulStaticCal extends MulCal{

	protected float[] ks;
	
	
	protected MulStaticCal(int[] times, float[] values, float startValue,EnumConStant.MulEndMode mulEndMode) {
		super(times, values, startValue,mulEndMode);
	}
	/**
	 * @param times 单位是豪秒
	 * @param values 变换值
	 * @param startValue
	 * @return: CaculationModel
	 */
	public static CaculationModel build(int[] times, float[] values, float startValue){
		return new MulStaticCal(times,values,startValue, EnumConStant.MulEndMode.WITH_END_CAL);
	}
	/**
	 * @param times 单位是豪秒
	 * @param values 变换值
	 * @param startValue
	 * @return: CaculationModel
	 */
	public static CaculationModel build(int[] times, float[] values, float startValue,EnumConStant.MulEndMode mulEndMode){
		return new MulStaticCal(times,values,startValue,mulEndMode);
	}
	@Override
	public float subCaculate(int time, int index, float lastValue) {
		return values[index];
	}


}
