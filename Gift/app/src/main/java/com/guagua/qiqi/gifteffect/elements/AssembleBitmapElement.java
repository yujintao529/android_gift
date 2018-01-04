package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jintao on 2015/7/30.
 * <p/>
 * 尽量少用，这个是组合bitmap，会创建一个位图。
 */
public class AssembleBitmapElement extends Element {

	private int mBitmapWidth;
	private int mBitmapHeight;

	//组合位图的rect
	private Rect mRange;//整个区域的面积
	private Board mBoard;

	//组合内容
	//元素
	Element[] mElements;
	//元素个数
	private int mIndex;
	//元素数量
	private int maxNumber;

	//测试数据
	private Paint test_paint = new Paint();

	public AssembleBitmapElement(IScene iScene, int width, int height) {
		super(iScene);
		this.mBitmapWidth = width;
		this.mBitmapHeight = height;
		mRange = new Rect(0, 0, width, height);
		mBoard = new Board(width, height);
		maxNumber = 50;
		mElements = new Element[maxNumber];
		initAnchor();
	}

	public final void addInnerElement(Element shape) {
		if (mIndex >= maxNumber) {
			Element[] now = new Element[maxNumber * 2];
			System.arraycopy(mElements, 0, now, 0, maxNumber);
			mElements = now;
			maxNumber = maxNumber * 2;
		}
		mElements[mIndex++] = shape;
	}
	@Override
	protected float anchorScaleX() {
		return mBoard.mRealBoard.getWidth() / 2;
	}

	@Override
	protected float anchorScaleY() {
		return mBoard.mRealBoard.getHeight() / 2;
	}

	@Override
	protected float anchorRotationX() {
		return mBoard.mRealBoard.getWidth() / 2;
	}

	@Override
	protected float anchorRotationY() {
		return mBoard.mRealBoard.getHeight() / 2;
	}
	/**
	 * @return 返回assemblerange
	 */
	public Rect getAssembleRange() {
		return new Rect(mRange);
	}

	@Override
	protected void draw(Canvas canvas, Matrix matrix, Paint paint, int timeDifference) {
		mBoard.clear();
		
		doInnerDraw(timeDifference);
		canvas.drawBitmap(mBoard.mRealBoard, matrix, paint);

		//测试
		test_paint.setColor(Color.BLUE);
		test_paint.setStrokeWidth(3);
		test_paint.setStyle(Paint.Style.STROKE);
		canvas.save();
		canvas.setMatrix(matrix);
		canvas.drawRect(mRange, test_paint);
		canvas.restore();
	}

	@Override
	protected void destroy() {
		super.destroy();
		for (int index = 0; index < mIndex; index++) {
			mElements[index].destroy();
		}

	}
	
	private void doInnerDraw(int timeDifference) {

		int i = 0;
		Element element;
		for (;;) {
			if (i < mIndex) {
				element = mElements[i++];
				element.doDraw(mBoard.mCanvas, timeDifference);
			}
			else {
				break;
			}
		}
	}

}
