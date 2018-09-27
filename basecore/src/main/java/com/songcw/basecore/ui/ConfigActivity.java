package com.songcw.basecore.ui;

import android.os.Bundle;

import com.songcw.basecore.R;
import com.songcw.basecore.mvp.BaseActivity;

/**
 * 配置页
 */
public class ConfigActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("配置");
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_config;
    }

    @Override
    protected void addSections() {
        addSection(new ConfigSection(this));
    }
}
