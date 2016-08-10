package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Canvas;

/**
 * Created by jintao on 2015/7/28.
 */

public interface ISignt {
    boolean isPlayEnd();
    void play(Canvas canvas);
    boolean readyForPlay();
    void playEnd();
    void destroy();
}
