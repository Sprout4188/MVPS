package com.jc.basecore.lifecycle;

import android.view.View;

import com.jc.basecore.mvp.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by Sprout at 2017/8/15
 */
public class ButterKnifeLifecycle implements ILifecycle {
    private Unbinder unbinder;

    public ButterKnifeLifecycle(BaseActivity target) {
        unbinder = ButterKnife.bind(target);
    }

    public ButterKnifeLifecycle(Object obj, BaseActivity source) {
        unbinder = ButterKnife.bind(obj, source);
    }

    public ButterKnifeLifecycle(Object obj, View source) {
        unbinder = ButterKnife.bind(obj, source);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onShow() {
    }

    @Override
    public void onHide() {
    }

    @Override
    public void onDestory() {
        if (unbinder != null) unbinder.unbind();
    }
}
