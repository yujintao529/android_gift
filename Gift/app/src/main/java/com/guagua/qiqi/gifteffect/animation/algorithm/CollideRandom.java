package com.guagua.qiqi.gifteffect.animation.algorithm;

import android.graphics.Rect;

/**
 * Created by jintao on 2015/7/6.
 *
 * 反弹后随机速率的碰撞盒子。
 * 
 * 还没有实现
 */
public class CollideRandom extends CollideBox {


    //xy速率，秒为单位
    private float xk;
    private float yk;

    private CollideRandom(Rect rect, float xk, float yk) {
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
     * 在一个盒子里进行碰撞反弹. 这个碰撞不带衰减
     * @param rect  盒子大小
     * @param xk x轴速率
     * @param yk y周速率
     * @return: RandomCollide
    */
    public static CollideRandom build(Rect rect,float xk,float yk){
        return new CollideRandom(rect,xk,yk);
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
