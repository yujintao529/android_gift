package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by yujintao on 15/7/12.
 */
public class CircleShape extends PaintShape {

    private float radius;

    public CircleShape(IScene iScene,float radius) {
        super(iScene);
        this.radius=radius;
    }

    @Override
    protected void draw(Canvas canvas, Matrix matrix, Paint paint, int timeDifference) {
        canvas.drawCircle(radius,radius,radius,paint);
    }

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
    
}
