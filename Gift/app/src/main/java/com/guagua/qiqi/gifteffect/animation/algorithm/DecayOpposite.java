package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行减小
 *
 */
@Deprecated
public class DecayOpposite extends CaculationModel {
    protected float beginRange;
    protected float indexRatio;
    protected float rangeRatio;
    protected DecayOpposite(EnumConStant.OppositeDecayBeginRange beginRange,EnumConStant.OppositeDecayIndexRatio indexRatio,EnumConStant.CommonRatio rangeRatio){
        this.beginRange=beginRange.value();
        this.indexRatio=indexRatio.value();
        this.rangeRatio=rangeRatio.value();
    }

    public DecayOpposite(float beginRange, float indexRatio, float rangeRatio) {
        this.beginRange = beginRange;
        this.indexRatio = indexRatio;
        this.rangeRatio = rangeRatio;
    }

    @Override
    public float caculate(int time) {
        return (float) (rangeRatio*(Math.pow(time/m_unit+beginRange,indexRatio)));
//        return (float) (rRatioValue*(Math.pow(time/m_unit,dRatioValue))+s);
    }
}
