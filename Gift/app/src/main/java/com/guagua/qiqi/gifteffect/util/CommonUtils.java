package com.guagua.qiqi.gifteffect.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/*************************************************************************************
* Module Name: CommonUtils</br>
* File Name: <b>CommonUtils.java</b></br>
* Description: 通用的工具方法</br>
* Author: 郁金涛</br>
* 版权 2008-2015，金华长风信息技术有限公司</br>
* 所有版权保护
* 这是金华长风信息技术有限公司未公开的私有源代码, 本文件及相关内容未经金华长风信息技术有限公司
* 事先书面同意，不允许向任何第三方透露，泄密部分或全部; 也不允许任何形式的私自备份。
***************************************************************************************/
public class CommonUtils {

	public static String getSubStringByLimit(String string, int number) {
		int weight = 0;
		int start = 0;
		final int finalWight = number;
		StringBuilder buffer = new StringBuilder();
		for (int length = string.length(); start < length;) {
			char c = string.charAt(start++);
			if (c > 0 && c < 127) {
				weight += 1;
			}
			else {
				weight += 2;
			}
			if (weight > finalWight) {
				break;
			}
			buffer.append(c);
		}
		if(weight>finalWight){
			buffer.append("...");
		}
		return buffer.toString();
	}
	
	
	public static int getDensityFontSize(Activity activity,int size){
		DisplayMetrics  outMetrics=new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		return (int) (outMetrics.densityDpi/320f*size);
	}	
	public static void showPhoneInfo(Activity activity){
		DisplayMetrics  outMetrics=new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
	}
	
}
