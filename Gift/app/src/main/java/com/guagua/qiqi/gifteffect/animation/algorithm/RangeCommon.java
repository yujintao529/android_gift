package com.guagua.qiqi.gifteffect.animation.algorithm;


/**
 *  一段时间内，执行一个过程，结束后维持最后状态
 */
public class RangeCommon extends CaculationModel{

	private float start;
	private float end;
	private float k;
	
	private boolean dir;
	private RangeCommon(float start,float end,float m){
		this.start=start;
		this.end=end;
		this.k=(end-start)/m;
		dir=start<end;
		
	}
	/**
	 * @param start
	 * @param end
	 * @param k  单位是豪秒啊啊啊啊啊
	 * @return
	 * @return: CaculationModel
	*/
	public static CaculationModel build(float start,float end,float k){
		return new RangeCommon(start, end, k);
	}
	@Override
	public float caculate(int time) {
		float val=k*time+start;
		if(dir&&val>=end){
			val=end;
		}else if(!dir&&val<=end){
			val=end;
		}
		return val;
	}
	
}
