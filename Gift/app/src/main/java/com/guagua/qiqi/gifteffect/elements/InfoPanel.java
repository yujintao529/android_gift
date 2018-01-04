package com.guagua.qiqi.gifteffect.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

/*************************************************************************************
* Module Name: InfoPanel</br>
* File Name: <b>InfoPanel.java</b></br>
* Description: 信息面板的组合bitmap对象</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class InfoPanel extends AssembleBitmapElement implements BitmapOnDrawListener{
	
	
	Rect mInfoPanel;//信息面板中背景图片的rect。
	
	Rect mMeasurerect;//背景图片里真实图片的矩形范围。
	
	Path mClipPath;//裁剪区域。背景图片里真实图片的圆角矩形范围或者是其他特殊范围
	public InfoPanel(IScene iScene, int width, int height,Rect rect) {
		super(iScene, width, height);
		mInfoPanel=rect;
	}
	public InfoPanel(IScene iScene, int width, int height) {
		super(iScene, width, height);
	}
	
	public Path setClipPath(Path path){
		if(mClipPath==null){
			mClipPath=new Path();
		}
		mClipPath.set(path);
		return getClipPath();
	}
	
	public Path getClipPath(){
		if(mClipPath==null){
			mClipPath=new Path();
		}
		return new Path(mClipPath);
	}
	public Rect setInfoPanelRect(int left,int top,int right,int bottom){
		if(mInfoPanel==null){
			mInfoPanel=new Rect();
		}
		mInfoPanel.set(left, top, right, bottom);
		return getInfoPanelRect();
	}
	public Rect getInfoPanelRect(){
		if(mInfoPanel==null){
			mInfoPanel=new Rect();
		}
		return new Rect(mInfoPanel);
	}
	public Rect setMeasureRect(int left,int top,int right,int bottom){
		if(mMeasurerect==null){
			mMeasurerect=new Rect();
		}
		mMeasurerect.set(left, top, right, bottom);
		return getMeasureRect();
	}
	public Rect getMeasureRect(){
		if(mMeasurerect==null){
			mMeasurerect=new Rect();
		}
		return new Rect(mMeasurerect);
	}
	@Override
	public boolean draw(Canvas canvas, Matrix matrix, Paint paint, Bitmap bitmap, int timeDifference) {
		if(mClipPath!=null&&!mClipPath.isEmpty())
			canvas.clipPath(mClipPath);
		return true;
	}

}
