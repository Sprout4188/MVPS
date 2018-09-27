package com.songcw.basecore.widget.progressbar;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.songcw.basecore.R;


/**
 * Create by Sprout at 2017/8/15
 */
public abstract class BaseProgressbar {

    public static boolean isShowing = false;
    /**
     * 对应界面的进度条布局
     */
    protected View baseView;
    protected Activity mActivity;
    protected Dialog dialog = null;

    public BaseProgressbar(Activity mActivity) {
        this.mActivity = mActivity;
        baseView = LayoutInflater.from(mActivity).inflate(loadXml(), null);
        if (dialog == null) dialog = new Dialog(mActivity, R.style.LoadingDialog);
        dialog.setContentView(baseView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 显示进度条
     */
    public void show() {
//        ActivityUtil.getRootView(mActivity).addView(baseView);
        if (dialog != null && !dialog.isShowing() && mActivity != null) {
            dialog.show();
            isShowing = true;
        }
    }

    /**
     * 隐藏进度条
     */
    public void disMiss() {
//        ActivityUtil.getRootView(mActivity).removeView(baseView);
        if (dialog != null && dialog.isShowing() && mActivity != null) {
            dialog.dismiss();
            isShowing = false;
            onDismiss();
        }
    }

    /**
     * 加载进度条布局
     */
    public abstract int loadXml();

    /**
     * 进度条消失时的回调
     */
    public abstract void onDismiss();
}
