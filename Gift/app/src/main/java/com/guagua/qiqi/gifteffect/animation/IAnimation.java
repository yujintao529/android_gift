package com.guagua.qiqi.gifteffect.animation;

import android.graphics.Matrix;
import android.graphics.Paint;

public interface IAnimation {
	boolean execute(Matrix matrix,Paint paint,int timeDifference);
}
