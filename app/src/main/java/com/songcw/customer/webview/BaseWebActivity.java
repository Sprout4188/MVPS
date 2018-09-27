package com.songcw.customer.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.customer.R;
import com.songcw.customer.application.Constant;

/**
 * @name employee
 * @class name：com.songcw.model
 * @class describe
 * @anthor wjb
 * @time 2018/9/10 11:35
 * @change
 * @chang time
 * @class describe
 */
public class BaseWebActivity extends BaseActivity {

    private String mTitle;
    private String mUrl;
    private String mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mTitle = getIntent().getStringExtra(Constant.ParamName.title);
        mUrl = getIntent().getStringExtra(Constant.ParamName.url);
        mContent = getIntent().getStringExtra(Constant.ParamName.content);
        super.onCreate(savedInstanceState);
        if(TextUtils.isEmpty(mTitle)){
            setTitle(getString(R.string.detail));
        }else {
            setTitle(mTitle);
        }

    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_base_web;
    }

    @Override
    protected void addSections() {
        addSection(new BaseWebSection(this, mTitle, mUrl, mContent));
    }
}
