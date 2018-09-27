package com.songcw.customer.home.mvp.view;

import android.os.Bundle;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.section.NewsListSection;

/**
 * @name employee
 * @class name：com.songcw.employee.home.activity
 * @class describe
 * @anthor wjb
 * @time 2018/9/5 10:43
 * @change
 * @chang time
 * @class 颂车头条列表 页面
 */
public class NewsListActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.songche_headline));
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void addSections() {
        addSection(new NewsListSection(this));
    }
}
