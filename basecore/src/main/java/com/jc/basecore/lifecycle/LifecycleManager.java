package com.jc.basecore.lifecycle;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Create by Sprout at 2017/8/15
 */
public class LifecycleManager implements ILifecycle {

    List<ILifecycle> list = new ArrayList<>();
    List<Disposable> disposables = new ArrayList<>();

    public void addLifecycle(ILifecycle lifecycle) {
        list.add(lifecycle);
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onCreate() {
        for (ILifecycle lifecycle : list) {
            lifecycle.onCreate();
        }
    }

    @Override
    public void onShow() {
        for (ILifecycle lifecycle : list) {
            lifecycle.onShow();
        }
    }

    @Override
    public void onHide() {
        for (ILifecycle lifecycle : list) {
            lifecycle.onHide();
        }
    }

    @Override
    public void onDestory() {
        for (ILifecycle lifecycle : list) {
            lifecycle.onDestory();
        }

        for (Disposable disposable : disposables) {
            if (!disposable.isDisposed()) disposable.dispose();
        }
    }
}
