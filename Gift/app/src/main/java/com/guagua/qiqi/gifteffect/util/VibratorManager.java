package com.guagua.qiqi.gifteffect.util;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by jintao on 2015/7/30.
 */
public class VibratorManager {
    public static void vibrator(Context context, long[] pattern, int numbers) {
        try {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(pattern, numbers);
        } catch (Exception e) {
            Logger.d("该设备不支持震动",e);
        }
    }

    public static void vibrator(Context context) {
        vibrator(context, new long[]{100, 300, 100, 300}, -1);
    }

    public static void cancle(Context context) {
        try {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.cancel();
        } catch (Exception e) {
            Logger.d("该设备不支持震动");
        }
    }
}
