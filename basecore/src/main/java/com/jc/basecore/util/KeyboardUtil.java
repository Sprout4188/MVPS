package com.jc.basecore.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jc.basecore.BaseApp;

/**
 * Created by Sprout on 2018/9/8
 */
public class KeyboardUtil {
    /**
     * 显示键盘
     *
     * @param view 获取键盘输入内容的View, 最好是EditText
     */
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏键盘
     *
     * @param view 当前布局树中的任意View
     */
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 键盘在显示与隐藏间切换
     *
     * @param view 当前布局树中的任意View
     */
    public static void toggleSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.toggleSoftInput(0, 0);
    }

    /**
     * 判断键盘是否显示
     */
    public static boolean isKeyboardShow() {
        InputMethodManager imm = (InputMethodManager) BaseApp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) return imm.isActive();
        return false;
    }

    /**
     * 再按一次是否在指定时间内
     */
    public static boolean isOverWhenPressAgain(long limitTime) {
        long lastPressTime = 0;
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
