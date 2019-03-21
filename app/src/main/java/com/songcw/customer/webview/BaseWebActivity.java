package com.songcw.customer.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.customer.R;
import com.songcw.customer.common.Constant;

public class BaseWebActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_base_web;
    }

    @Override
    protected void addSections() {
        String mUrl = getIntent().getStringExtra(Constant.ParamName.url);
        addSection(new BaseWebSection(this, mUrl));
    }
}
