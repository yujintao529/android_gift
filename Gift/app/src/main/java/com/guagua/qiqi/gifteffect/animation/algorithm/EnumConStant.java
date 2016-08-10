package com.guagua.qiqi.gifteffect.animation.algorithm;

/**
 * Created by yujintao on 15/7/4.
 */
public class EnumConStant {
    public static enum CommonRatio

    {

        COORDINATE_5(5f),
        COORDINATE_20(20f),
        COORDINATE_50(50f),
        COORDINATE_100(100f),
        COORDINATE_150(150f),
        COORDINATE_200(200f),
        COORDINATE_250(250f),
        COORDINATE_500(500f),
        COORDINATE_1000(1000f),
        DEFAULT(1f),
        ALPHA(255f);
        private float value;

        private CommonRatio(float value) {
            this.value = value;
        }

        public float value() {
            return value;
        }
    }


    /**
     * 这个用于增加衰变使用，指数系数，衰变系数
     */
    public static enum DecayRatio {
        //下面是增长的
        QUICKER_CLIP(.1f),
        QUICK_CLIP(.25f),
        SLOW_CLIP(.75f),
        SLOWER_CLIP(.9f),
        COMMON_CLIP(.5f),
        QUICKER_ADD(3f),
        QUICK_ADD(5f),
        SLOW_ADD(1.5f),
        SLOWER_ADD(1.2f),
        COMMON_ADD(2f);
        private float value;

        private DecayRatio(float value) {
            this.value = value;
        }

        public float value() {
            return value;
        }
    }

    /**
     * 这个用于减小衰变使用
     */
    public static enum OppositeDecayIndexRatio {

        SLOWER_MORE_POINT1(.1f),
        SLOWER_POINT25(.25f),
        SLOW_POINT5(.5f),
        QUICKER_5(-5f),
        QUICK_2(-2f),
        COMMON_1(-1f);
        private float value;

        private OppositeDecayIndexRatio(float value) {
            this.value = value;
        }

        public float value() {
            return value;
        }

    }

    public static enum OppositeDecayBeginRange {

        COMMON_POINT4(4f),
        COMMON_POINT2(2f),
        COMMON_1(1f);


        private float value;

        private OppositeDecayBeginRange(float value) {
            this.value = value;
        }

        public float value() {
            return value;
        }
    }
    public static enum WaveMode {
        SMALL(),
        MIDDLE();
        private WaveMode(){
        }
    }

    public static enum MulEndMode{
        WITH_END_VALUE(),
        WITH_END_CAL(),
        WITH_START_VALUE();
    }

}
