package com.guagua.qiqi.gifteffect.util;


/**
 * Created by yujintao on 15/7/1.
 */
public class Logger {
    public static String TAG = "GiftEffect";
    
    public static void d(String Tag,String message){
    	LogUtils.d(Tag, message);
    }
    
    public static void d(String Tag, String message, Throwable throwable){
    	LogUtils.d(Tag, message,throwable);
    }
    
    public static void d(String message){
       d(TAG,message);     
    }
    public static void d(String message,Throwable throwable){
        d(TAG,message,throwable);
     }
    public static void d(String message,Object... args){
        d(TAG, String.format(message, args));
    }

    public static void d(String tag,String message,Object... args){
        d(tag, String.format(message, args));
    }
}
