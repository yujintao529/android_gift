package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Shader;

/**
 * Created by yujintao on 15/7/11.
 *
 * 简单起边，采用shader和color的方式，不采用colorFilter。如果后期需要高亮或者饱和度等的画可以采用colorfiler
 */
public abstract class PaintShape extends Element {
    public PaintShape(IScene iScene) {
        super(iScene, ANIMATION_MODE_CANVAS);
        paint.setDither(true);
        paint.setAntiAlias(true);
    }

    public void setColor(int  color) {
        paint.setColor(color);
    }


    public void setShader(Shader shader) {
        paint.setShader(shader);
    }


}
