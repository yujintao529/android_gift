package com.guagua.qiqi.gifteffect.util;

import android.util.Log;

/**
 * Created by yujintao on 15/7/1.
 */
public class LogUtils {
    public static String TAG="GIFTEFFECT";
    public static void d(String Tag,String message){
        Log.d(Tag,message);

    }
    public static void d(String message){
        Log.d(TAG,message);
    }
    public static void d(String message,Object... args){
        d(TAG, String.format(message, args));
    }

    public static void d(String tag,String message,Object... args){
        d(tag, String.format(message, args));
    }
    public static void d(String Tag,String message,Throwable throwable){
        Log.d(Tag, message,throwable);

    }
}
