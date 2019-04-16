package com.jc.basecore.threeplatform;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.jc.basecore.BaseApp;

/**
 * Created by Sprout on 2018/8/21
 */
public class LifecycleThreePlatform implements ThreePlatform {

    @Override
    public void init(Context context) {
        ((BaseApp) context).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManager.getInstance().removeActivity(activity);
            }
        });
    }
}
