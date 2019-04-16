package com.jc.basecore.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.ViewGroup;

/**
 * Create by Sprout at 2017/8/15
 */
public class ActivityUtil {

    /**
     * 获取Activity的DecorView
     */
    public static ViewGroup getRootView(Activity activity ){
        return (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    /**
     * 打开本应用的设置界面
     */
    public static void startAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

    private static long lastPressTime = 0;
    /**
     * 再按一次是否在指定时间内
     *
     * @param limitTime
     * @return
     */
    public static boolean isOverWhenPressAgain(long limitTime) {
        if (limitTime < 500)
            limitTime = 500;
        if ((System.currentTimeMillis() - lastPressTime) > limitTime) {
            lastPressTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }
}
