package com.jc.basecore.lifecycle;

import com.hwangjr.rxbus.RxBus;
import com.jc.basecore.mvp.BaseSection;

/**
 * Create by Sprout at 2017/8/15
 */
public class RxBusLifecycle implements ILifecycle {
    private BaseSection baseSection;

    public RxBusLifecycle(BaseSection baseSection) {
        this.baseSection = baseSection;
        RxBus.get().register(baseSection);
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
        if (baseSection != null) RxBus.get().unregister(baseSection);
    }
}
