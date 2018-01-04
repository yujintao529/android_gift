package com.guagua.qiqi.gifteffect.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.guagua.qiqi.gifteffect.animation.algorithm.MathCommonAlg;

public class BitmapUtils {
	public static void destroy(Bitmap bitmap) {
		if (bitmap == null || bitmap.isRecycled()) {
			return;
		}
		bitmap.recycle();
	}

	public static Bitmap decodeBitmap(Context context, int id) {
		return BitmapFactory.decodeResource(context.getResources(), id);
	}

	public static Bitmap craeteBitmap(int width,int height){
		return Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
	}
	public static Bitmap createBitmap(Bitmap bitmap,int width,int height,int density){
		float scaX = width*1f / bitmap.getWidth();
		float scaY = height*1f / bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.setScale(scaX, scaY);
		final Bitmap result=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		result.setDensity(density);
		return result;
	}
	public static Bitmap createBitmap(Bitmap bitmap,int width,int height){
		float scaX = width*1f / bitmap.getWidth();
		float scaY = height*1f / bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.setScale(scaX, scaY);
		final Bitmap result=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return result;
	}
	public static int randomColor() {
		int red = MathCommonAlg.rangeRandom(0, 255);
		int yellow = MathCommonAlg.rangeRandom(0, 255);
		int blue = MathCommonAlg.rangeRandom(0, 255);
		return (255 << 24) | (red << 16) | (yellow << 8) | (blue);
	}

	public static Rect correctBitmapRect(Bitmap bitmap, Rect rect, float scale) {
		Rect correct = new Rect(rect);
		correct.left = (int) (rect.left - bitmap.getWidth() * scale * .5f + 0.5f);
		correct.right = (int) (rect.right - bitmap.getWidth() * scale * .5f + 0.5f);
		correct.top = (int) (rect.top - bitmap.getHeight() * scale * .5f + 0.5f);
		correct.bottom = (int) (rect.bottom - bitmap.getHeight() * scale * .5f + 0.5f);
		return correct;
	}

	
	public static String limitString(String oriStr, int limit) {
		String result = oriStr;
		if (oriStr.length() > limit) {
			result = oriStr.substring(0, limit - 1) + "...";
		}
		return result;
	}
	
}
