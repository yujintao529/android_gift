package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by jintao on 2015/7/29.
 */
public class ComputeContainer {

    public static final int ALPHA=0;
//    public static final int

//    private
    public ComputeContainer(){

    }

    public final boolean compute(Matrix matrix,Paint paint,long timeDifference){
        return true;
    }

    public interface ComputeInterface{
        boolean compute();
    }
}
