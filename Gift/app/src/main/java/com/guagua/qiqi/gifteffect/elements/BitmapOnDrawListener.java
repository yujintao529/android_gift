package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/*************************************************************************************
* Module Name: OnDrawListener</br>
* File Name: <b>OnDrawListener.java</b></br>
* Description: TODO</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public interface BitmapOnDrawListener {
	
	/**
	 * 一个绘图的listener
	 * @param canvas
	 * @param matrix
	 * @param paint
	 * @param timeDifference
	 * @return: boolean 返回true的话,
	*/
	boolean draw(Canvas canvas, Matrix matrix, Paint paint,Bitmap bitmap, int timeDifference);
}
