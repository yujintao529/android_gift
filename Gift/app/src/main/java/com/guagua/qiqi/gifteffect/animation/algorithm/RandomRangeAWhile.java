package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 */
public class RandomRangeAWhile extends RandomRange {

    private float record;
    private float last;
    private float curValue;
    private float lastTime;
    private boolean refresh;
    /**
     * 妙为单位
     * @param b
     * @param y
     * @param last
     */
    private RandomRangeAWhile(int b, int y, float last) {
        super(b, y);
        if (last <= 0) {
            throw new IllegalArgumentException("last must not liiter 0");
        }
        this.last = last*1000;
        record = 0;
        lastTime=0;
        refresh=true;
    }

    @Override
    public float caculate(int time) {
        float left = time-lastTime;
        lastTime=time;
        if(refresh){
            curValue=super.caculate(time);
            refresh=false;
        }
        if((record+=left)>last){
            record-=last;
            refresh=true;
        }
        return curValue;
    }

    /**
     * @param b 起始值
     * @param y 最大值
     * @param time 单位是秒
     * @return
     * @return: RandomRange
    */
    public static RandomRange build(int b, int y, float time) {
        return new RandomRangeAWhile(b, y, time);

    }
}
