package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行减小
 *
 */
@Deprecated
public class DecayOppositeAWhile extends DecayOpposite {
    protected float last;
    protected DecayOppositeAWhile(EnumConStant.OppositeDecayBeginRange beginRange, EnumConStant.OppositeDecayIndexRatio indexRatio, EnumConStant.CommonRatio rangeRatio,float last) {
        super(beginRange, indexRatio, rangeRatio);
        this.last = last * m_unit;
    }

    public DecayOppositeAWhile(float beginRange, float indexRatio, float rangeRatio, float last) {
        super(beginRange, indexRatio, rangeRatio);
        this.last = last*m_unit;
    }

    @Override
    public float caculate(int time) {
        return (float) (rangeRatio*(Math.pow(time%last/m_unit+beginRange,indexRatio)));
    }
    public static CaculationModel build(EnumConStant.OppositeDecayBeginRange beginRange, EnumConStant.OppositeDecayIndexRatio indexRatio, EnumConStant.CommonRatio rangeRatio,float last){
        return new DecayOppositeAWhile(beginRange,indexRatio,rangeRatio,last);
    }
    /**
     * 
     * TODO
     * @param beginRange
     * @param indexRatio
     * @param rangeRatio
     * @param last
     * @return
     * @return: CaculationModel
     */
    public static CaculationModel build(float beginRange, float indexRatio, float rangeRatio,float last){
        return new DecayOppositeAWhile(beginRange,indexRatio,rangeRatio,last);
    }
}
