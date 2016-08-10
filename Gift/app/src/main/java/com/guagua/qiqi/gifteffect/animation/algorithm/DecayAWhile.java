package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行增长，速率逐渐变慢dratio主要是控制衰变的时间长度的曲线
 * rRatiox^dRatio + s;最小值
 */
@Deprecated
public class DecayAWhile extends Decay {
    private int last;

    protected DecayAWhile(EnumConStant.CommonRatio rRatio, float s, EnumConStant.DecayRatio dRatio, int last) {
        super(rRatio, s, dRatio);
        this.last = (int) (last*m_unit);
    }
    protected DecayAWhile(float r, float s, EnumConStant.DecayRatio dRatio, int last) {
        super(r, s, dRatio);
        this.last = (int) (last*m_unit);
    }
    @Override
    public float caculate(int time) {
        return (float) (rRatioValue * (Math.pow(time % last / m_unit, dRatioValue)) + s);
    }
    public static CaculationModel build(EnumConStant.CommonRatio rRatio, float s, EnumConStant.DecayRatio dRatio, int last){
        return new DecayAWhile(rRatio,s,dRatio,last);
    }
    
    
    /**
     * 
     * 
     * @param r
     * @param s
     * @param dRatio
     * @param last
     * @return
     * @return: CaculationModel
     */
    public static CaculationModel build(float r, float s, EnumConStant.DecayRatio dRatio, int last){
        return new DecayAWhile(r,s,dRatio,last);
    }
}
