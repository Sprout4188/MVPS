package com.songcw.basecore.http;

import android.app.Activity;

import com.songcw.basecore.widget.progressbar.BaseProgressbar;
import com.songcw.basecore.widget.progressbar.CircleProgressBar;

/**
 * Create by Sprout at 2017/8/15
 */
public class InterceptorUtil {

    public static IInterceptor buildCircleProgressbar(final BaseProgressbar progressbar) {
        return new IInterceptor() {
            @Override
            public void runOnStart() {
                if (!BaseProgressbar.isShowing) progressbar.show();
            }

            @Override
            public void runOnComplete() {
                if (BaseProgressbar.isShowing) progressbar.disMiss();
            }
        };
    }

    /**
     * 默认Loading菊花圈
     */
    public static IInterceptor defaultLoadingInterceptor(Activity activity) {
        return InterceptorUtil.buildCircleProgressbar(new CircleProgressBar(activity, "加载中"));
    }
}
