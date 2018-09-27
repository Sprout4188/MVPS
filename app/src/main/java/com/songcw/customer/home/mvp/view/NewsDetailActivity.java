package com.songcw.customer.home.mvp.view;

import android.os.Bundle;

import com.songcw.customer.webview.BaseWebActivity;
import com.songcw.customer.home.mvp.section.NewsDetailSection;
import com.songcw.customer.application.Constant;

/**
 * @name employee
 * @class name：com.songcw.employee.home.activity
 * @class describe
 * @anthor wjb
 * @time 2018/9/5 18:11
 * @change
 * @chang time
 * @class 新闻/头条/Banner/广告 详情
 */
public class NewsDetailActivity extends BaseWebActivity{

    protected String mTitle;
    protected String mUrl;
    protected String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTitle = getIntent().getStringExtra(Constant.ParamName.title);
        mUrl = getIntent().getStringExtra(Constant.ParamName.url);
        mContent = getIntent().getStringExtra(Constant.ParamName.content);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentLayout() {
        return super.setContentLayout();
    }

    @Override
    protected void addSections() {
        addSection(new NewsDetailSection(this,mTitle,mUrl,mContent));
    }
}
