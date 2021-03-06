package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 * 范围时间内进行减小,减小的速率逐渐减慢。简单期间
 * y=ratio(x+1)^-1 ratio也是作为初始值使用，主要是减小的速率问题
 * 使用下面几个常用的
 * 小于－1 。－1是1/2一秒内缩减，－2是3／4一秒内缩减，－3约为8/9 －4约为15/16
 * 大于－1小于0。－1/2是不足1／3缩减，－1/3是不足1／4缩减，－2/3是约为1/3的缩减
 */
public class DecayOpposite2AWhile extends CaculationModel {
    protected float index;
    protected float ratio;
    protected float last;
    private DecayOpposite2AWhile(float index, float ratio,float last) {
        this.index = index;
        this.ratio = ratio;
        this.last=last;
    }

    @Override
    public float caculate(int time) {
        return (float) (ratio*(Math.pow(time%(last*m_unit)/m_unit+1,index)));
    }
    
    /**
     * @param index 
     * @param ratio
     * @param last
     * @return
     * @return: CaculationModel
    */
    public static CaculationModel build(float index, float ratio,float last){
    	return new DecayOpposite2AWhile(index,ratio,last);
    }
}
