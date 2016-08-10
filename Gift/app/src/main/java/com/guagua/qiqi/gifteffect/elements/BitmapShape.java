package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.guagua.qiqi.gifteffect.util.BitmapUtils;

/**
 * Created by yujintao on 15/7/1.
 */
public class BitmapShape extends Element {
	private static final String TAG = BitmapShape.class.getSimpleName();

	protected Bitmap mShape;

	private BitmapOnDrawListener drawListener;

	public BitmapShape(Bitmap bitmap, IScene iscene) {
		super(iscene, ANIMATION_MODE_MATRIX);
		this.mShape = bitmap;
		initAnchor();
	}

	@Override
	protected void draw(Canvas canvas, Matrix matrix, Paint paint, int timeDifference) {
		if (drawListener == null || drawListener.draw(canvas, matrix, paint, mShape, timeDifference)) {
			canvas.drawBitmap(mShape, matrix, paint);
		}

	}

	public void setBitmapOnDrawListener(BitmapOnDrawListener drawListener) {
		this.drawListener = drawListener;
	}

	@Override
	protected float anchorScaleX() {
		return mShape.getWidth() / 2;
	}

	@Override
	protected float anchorScaleY() {
		return mShape.getHeight() / 2;
	}

	@Override
	protected float anchorRotationX() {
		return mShape.getWidth() / 2;
	}

	@Override
	protected float anchorRotationY() {
		return mShape.getHeight() / 2;
	}

	public float getBitmapWidth() {
		return mShape.getWidth();
	}

	public float getBitmapHeight() {
		return mShape.getHeight();
	}

	@Override
	protected void destroy() {
		super.destroy();
		BitmapUtils.destroy(mShape);
		mShape = null;
	}
}
