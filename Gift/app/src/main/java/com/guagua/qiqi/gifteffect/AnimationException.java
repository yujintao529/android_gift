package com.guagua.qiqi.gifteffect;

/**
 * Created by yujintao on 15/7/5.
 */
public class AnimationException extends RuntimeException {	
	private static final long serialVersionUID = 1L;

	public AnimationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AnimationException(Throwable throwable) {
        super(throwable);
    }

    public AnimationException() {
    }

    public AnimationException(String detailMessage) {
        super(detailMessage);
    }
}
