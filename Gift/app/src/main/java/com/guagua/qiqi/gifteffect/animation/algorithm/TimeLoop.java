package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 *
 * 设定轮训一次的时间。sin类型曲线-1-0-1循环
 * 
 */
@Deprecated
public class TimeLoop extends CaculationModel {
    private int last;
    private float r;
    private float m;
    private float s;
    private float e;
    private EnumConStant.CommonRatio unit;
    private TimeLoop(int last,EnumConStant.CommonRatio unit){
       this(last,unit,-1,1);
    }
    private TimeLoop(int last,EnumConStant.CommonRatio unit,float start,float end){
        this.last=last;
        s=start;
        e=end;
        m=(s+e)/2;
        r=m-s;
        this.unit=unit;

    }
    
    public static CaculationModel build(int last , EnumConStant.CommonRatio unit){
        return  new TimeLoop(last,unit);
    }
    
    /**
     * 设定轮训一次的时间。sin类型曲线-1-0-1循环
     * 但是这个不能反向进行，也就是重复一个动作
     * @param last 轮训的时间
     * @param unit 系数，也就是1*多少。
     * @param start 轮训的最小值，
     * @param end 轮训的最大值
     * @return: CaculationModel
    */
    public static CaculationModel build(int last , EnumConStant.CommonRatio unit,float start,float end){
        return new TimeLoop(last,unit,start,end);
    }

    @Override
    public float caculate(int time) {
       return  (float) (r*Math.sin((time/m_unit/(last/2)-HALF_VALUE)*Math.PI)+m)*unit.value();
    }
}
