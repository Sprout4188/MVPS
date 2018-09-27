package com.songcw.customer.main.mvp.section;

import android.os.SystemClock;

import com.songcw.basecore.mvp.BaseSection;
import com.songcw.customer.main.mvp.view.LauncherView;
import com.songcw.customer.main.mvp.presenter.LauncherPresenter;
import com.songcw.customer.main.mvp.view.MainActivity;

public class LauncherSection extends BaseSection<LauncherPresenter> implements LauncherView {

    public LauncherSection(Object source) {
        super(source);
    }

    @Override
    public LauncherPresenter onCreatePresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(5000L);
                startActivity(MainActivity.class);
                finish();
                super.run();
            }
        }.start();
    }
}