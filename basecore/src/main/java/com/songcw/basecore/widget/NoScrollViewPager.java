package com.songcw.basecore.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Sprout on 2017/8/23
 * 自定义ViewPager, 可根据需要设置是否左右滑动
 */

public class NoScrollViewPager extends ViewPager {

    private boolean noScroll = true;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否禁用ViewPager左右滑动
     * @param noScroll true表示禁用
     */
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !noScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !noScroll && super.onTouchEvent(ev);
    }
}
