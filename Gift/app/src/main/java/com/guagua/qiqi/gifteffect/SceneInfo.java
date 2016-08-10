package com.guagua.qiqi.gifteffect;

import android.graphics.Bitmap;

/**
 * Created by yujintao on 15/7/5.
 *
 * 用于存储需要动画的信息
 */
public class SceneInfo {
	public String sender;
	public String receiver;
	public String unit;
	public int animType;
	public int num;
	public int effectLevel;//要播放礼物动画的级别
	public String resPath;//路径起始的scheme要符合以下规则
							//HTTP("http://"), HTTPS("https://"), FILE("file://"), 
							//CONTENT("content://"), ASSETS("assets://"), DRAWABLE("drawable://")
//	private  Reference<Bitmap> giftBitmap;
	private Bitmap giftBitmap;
	public void setGiftBitmap(Bitmap bitmap){
//		giftBitmap=new SoftReference<Bitmap>(bitmap);
		giftBitmap=bitmap;
	}
	
	public Bitmap getGiftBitmap(){
//		Bitmap bitmap=null;
//		if(giftBitmap!=null){
//			bitmap=giftBitmap.get();
//		}
		return giftBitmap;
	}
	
	@Override
	public String toString() {
		return "SceneInfo [sender=" + sender + ", receiver=" + receiver + ", unit=" + unit + ", animType=" + animType + ", num=" + num
				+ ", effectLevel=" + effectLevel + ", giftBitmap=" + giftBitmap + "]";
	}
	
}	
