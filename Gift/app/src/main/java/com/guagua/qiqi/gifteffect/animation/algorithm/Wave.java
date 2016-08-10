package com.guagua.qiqi.gifteffect.animation.algorithm;

import com.guagua.qiqi.gifteffect.AnimationException;

/**
 * Created by jintao on 2015/7/6.
 * 波浪衰减。但是这个参数太多不用了。
 */
@Deprecated
public class Wave extends CaculationModel {
    protected float ratio;//系数

    protected float b;//最小值
    protected float e;//最大值
    protected float r;//波动范围
    protected float m;//范围补加
    protected float endM;//最后速度
    protected float t;//系数。默认1，系数越大，周期越快
    protected float s;//默认的值，一般为0吧....
    private int number;//变化次数
    private int mCur;
    private float decayValue;
    private EnumConStant.WaveMode waveMode;
    protected boolean agine;
    protected Wave(EnumConStant.CommonRatio ratio, float t, float s, float b, float e, int number,EnumConStant.WaveMode mode,boolean agine) {
        this.ratio = ratio.value();
        this.b = b;
        this.e = e;
        this.waveMode=mode;
        m = (b + e) / 2;
        r = e - m;
        this.t = t;
        this.s = s;
        this.number = number;
        this.agine=agine;
        switch (waveMode){
            case MIDDLE:
                endM=m;
                decayValue=(e-b)/2/number;
                break;
            case SMALL:
                endM=b;
                decayValue=(e-b)/number;
                break;
        }
    }


    public void setNumber(int number) {
        if (number <= 0) {
            throw new AnimationException("wave次数必须大于0");
        }
        this.number = number;
    }

    private void decay() {
        switch (waveMode){
            case SMALL:
                e=e-decayValue;
                m = (b + e) / 2;
                r = e - m;//
                break;
            case MIDDLE:
                e-=decayValue;
                b+=decayValue;
                m = (b + e) / 2;
                break;
        }
    }
    //返回初始状态
    private void reset(){
        mCur=0;
        switch (waveMode){
            case SMALL:
                e=decayValue*number+b;
                m = (b + e) / 2;
                r = e - m;//
                break;
            case MIDDLE:
                e=decayValue*number+endM;
                b=endM-decayValue*number;
                m = (b + e) / 2;
                r = e - m;
                break;
        }
    }
    public static CaculationModel build(EnumConStant.CommonRatio ratio, float t, float s, float b, float e, int number,EnumConStant.WaveMode mode,boolean agine){
        return new Wave(ratio,t,s,b,e,number,mode,agine);
    }



    @Override
    public float caculate(int time) {
        final int left = (int) (time / m_unit/t);
        if (mCur != left) {
            mCur++;
            if (mCur <number) {
                decay();
            }else if(mCur==number){
                r=0;
                m=endM;
            }else{
                if(agine){
                    reset();
                }
            }
        }
        return (float) ((r * Math.sin(t * (time / m_unit + s) * PI_2) + m) * ratio);
    }
}
