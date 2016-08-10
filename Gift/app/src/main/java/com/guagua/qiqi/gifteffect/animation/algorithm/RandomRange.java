package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 
 */
public class RandomRange extends CaculationModel {
    private int b;
    private int y;
    public RandomRange(int b,int y){
        this.b=b;
        this.y=y;
    }
    @Override
    public float caculate(int time) {
        float result=MathCommonAlg.rangeRandom(b,y);
        return result;
    }
}
