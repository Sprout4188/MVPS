package com.songcw.basecore;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.songcw.basecore.threeplatform.LifecycleThreePlatform;
import com.songcw.basecore.threeplatform.LogThreePlatform;
import com.songcw.basecore.threeplatform.ThreePlatformManager;
import com.songcw.basecore.threeplatform.UtilThreePlatform;

/**
 * Create by Sprout at 2017/8/15
 */
public class BaseApp extends MultiDexApplication {

    private static Context context = null;

    public static Context getContext() {
        return context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        new ThreePlatformManager(this)
                .addThreePlatform(new LogThreePlatform())       //Logger
                .addThreePlatform(new LifecycleThreePlatform()) //生命周期管理
                .addThreePlatform(new UtilThreePlatform())      //工具类
                .init();
    }
}
