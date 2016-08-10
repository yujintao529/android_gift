package com.guagua.qiqi.gifteffect.animation.algorithm;

import com.guagua.qiqi.gifteffect.AnimationException;

public class HudieCal extends CaculationModel {
	private C_X c_x;

	private C_Y c_y;
	
	public HudieCal() {
		c_x = new C_X();
		c_y = new C_Y();
	}

	@Override
	public float caculate(int time) {
		throw new AnimationException("hudie cal must not execute ");
	}

	public void setCX(CaculationModel caculationModel){
		c_x.real=caculationModel;
	}

	public void setCY(CaculationModel caculationModel) {
		c_y.real=caculationModel;
	}

	public CaculationModel newCX() {
		return c_x;
	}

	public CaculationModel newCY() {
		return c_y;
	}

	protected class C_X extends CaculationModel {
		public CaculationModel real;
		@Override
		public float caculate(int time) {
			return 0;
		}
	}

	protected class C_Y extends CaculationModel {
		public CaculationModel real;

		@Override
		public float caculate(int time) {
			return 0;
		}
	}

	public class C_R extends CaculationModel {

		@Override
		public float caculate(int time) {
			return 0;
		}

	}

}
