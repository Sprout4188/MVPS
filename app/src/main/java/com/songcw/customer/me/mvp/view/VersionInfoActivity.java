package com.songcw.customer.me.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.customer.R;
import com.songcw.customer.me.mvp.section.VersionInfoSection;

public class VersionInfoActivity extends BaseActivity {

    @Override
    protected void addSections() {
        addSection(new VersionInfoSection(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("版本信息");
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_version_info;
    }
}