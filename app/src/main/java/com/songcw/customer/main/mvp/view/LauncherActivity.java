package com.songcw.customer.main.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.customer.R;
import com.songcw.customer.util.BusinessUtil;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void addSections() {
//        addSection(new LauncherSection(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BusinessUtil.hasLoginData(this)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        SystemClock.sleep(500L);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_launcher;
    }
}