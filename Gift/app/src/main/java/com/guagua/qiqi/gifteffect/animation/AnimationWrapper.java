package com.guagua.qiqi.gifteffect.animation;

import com.guagua.qiqi.gifteffect.animation.algorithm.CaculationModel;
import com.guagua.qiqi.gifteffect.util.Logger;

import android.graphics.Matrix;
import android.graphics.Paint;

public class AnimationWrapper implements IAnimation {

	private CaculateCommonHandle caculateCommonHandle;

	private float mScaleX = 0;
	private float mScaleY = 0;
	private float mRotationX = 0;
	private float mRotationY = 0;

	public AnimationWrapper setScaleAnchor(float scaleX, float scaleY) {
		this.mScaleX = scaleX;
		this.mScaleY = scaleY;
		return this;
	}

	public AnimationWrapper setRotationAnchor(float rotationX, float rotationY) {
		this.mScaleX = rotationX;
		this.mScaleY = rotationY;
		return this;
	}

	//set get 方法

	public CaculateCommonHandle getCaculateCommonHandle() {
		return caculateCommonHandle;
	}

	public void setCaculateCommonHandle(CaculateCommonHandle caculateCommonHandle) {
		this.caculateCommonHandle = caculateCommonHandle;
	}

	public void setAlphaAnimation(CaculationModel caculationModel) {
		if (caculateCommonHandle == null) {
			caculateCommonHandle = new CaculateCommonHandle();
		}
		caculateCommonHandle.c_alpha = caculationModel;
	}

	public void setRotationAnimation(CaculationModel caculationModel) {
		if (caculateCommonHandle == null) {
			caculateCommonHandle = new CaculateCommonHandle();
		}
		caculateCommonHandle.c_rotation = caculationModel;
	}

	public void setScaleAnimation(CaculationModel scaleX, CaculationModel scaleY, CaculationModel scale) {
		if (caculateCommonHandle == null) {
			caculateCommonHandle = new CaculateCommonHandle();
		}
		caculateCommonHandle.c_scale = scale;
		caculateCommonHandle.c_scale_x = scaleX;
		caculateCommonHandle.c_scale_y = scaleY;
	}

	public void setXYAnimation(CaculationModel x, CaculationModel y) {
		if (caculateCommonHandle == null) {
			caculateCommonHandle = new CaculateCommonHandle();
		}
		caculateCommonHandle.c_x = x;
		caculateCommonHandle.c_y = y;
	}

	public void setColorAnimation(CaculationModel color) {
		if (caculateCommonHandle == null) {
			caculateCommonHandle = new CaculateCommonHandle();
		}
		caculateCommonHandle.c_color = color;
	}

	@Override
	public boolean execute(Matrix matrix, Paint paint, int timeDifference) {
		if (caculateCommonHandle == null)
			return true;
		float value = 255f;
		if (caculateCommonHandle.c_alpha != null) {
			value = caculateCommonHandle.c_alpha.caculate(timeDifference);
		}
		if (value <=0) {
			return false;
		}
		
		paint.setAlpha((int) value);
		matrix.reset();
		if (caculateCommonHandle.c_rotation != null) {
			value = caculateCommonHandle.c_rotation.caculate(timeDifference);
			matrix.setRotate(value, mRotationX, mRotationY);
		}
		//如果想部分缩放的话,设置c_scale_x和c_scale_y不设置c_scale
		if (caculateCommonHandle.c_scale != null) {
			float scale = caculateCommonHandle.c_scale.caculate(timeDifference);
			if (scale <= 0) {
				return false;
			}
			matrix.preScale(scale, scale, mScaleX, mScaleY);
		}
		else {
			float scalex = 1;
			float scaley = 1;
			if (caculateCommonHandle.c_scale_x != null) {
				scalex = caculateCommonHandle.c_scale_x.caculate(timeDifference);
			}
			if (caculateCommonHandle.c_scale_y != null) {
				scaley = caculateCommonHandle.c_scale_y.caculate(timeDifference);
			}
			matrix.preScale(scalex, scaley, mScaleX, mScaleY);
		}
		float x=0, y=0;
		if (caculateCommonHandle.c_x != null) {
			x = caculateCommonHandle.c_x.caculate(timeDifference);
			Logger.d("animation wrraper x "+x);
		}
		if (caculateCommonHandle.c_y != null) {
			y = caculateCommonHandle.c_y.caculate(timeDifference);
			Logger.d("animation wrraper y "+y);
		}
		
		matrix.postTranslate(x,y);
		return true;
	}

}
