package com.guagua.qiqi.gifteffect.animation.algorithm;

import android.graphics.Rect;

/**
 * Created by jintao on 2015/7/6.
 *
 * 最简单的盒子碰撞.碰撞后，进行速率变反
 */
public class CollideBoxSimple extends CollideBox {


    //xy速率，秒为单位
    private float xk;
    private float yk;

    private CollideBoxSimple(Rect rect,float xk,float yk ) {
        super(rect);
        this.xk=xk;
        this.yk=yk;
    }

    @Override
    protected void refresh(int category) {
        switch (category){
            case CATEGORY_X:
                xk*=-1;
                break;
            case CATEGORY_Y:
                yk*=-1;
                break;
        }
    }

    /**
     *
     * @param rect 范围
     * @param xk 秒为单位的速率
     * @param yk 秒为单位的速率
     * @return
     */
    public static CollideBoxSimple build(Rect rect,float xk,float yk){
        return new CollideBoxSimple(rect,xk,yk);
    }
    @Override
    protected float caculateX(float timeDifference,float realTime) {
        return xk/m_unit*timeDifference+mStartX;
    }

    @Override
    protected float caculateY(float timeDifference,float realTime) {
        return yk/m_unit*timeDifference+mStartY;
    }
}
