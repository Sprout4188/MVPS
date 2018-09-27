package com.songcw.customer.home.mvp.section;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.songcw.customer.webview.BaseWebSection;
import com.songcw.customer.home.mvp.presenter.NewsDetailPresenter;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.section
 * @class describe
 * @anthor wjb
 * @time 2018/9/10 14:27
 * @change
 * @chang time
 * @class describe
 */
public class NewsDetailSection extends BaseWebSection<NewsDetailPresenter>{

    protected String mTitle;
    protected String mUrl;
    protected String mContent;

    public NewsDetailSection(Object source) {
        this(source,"","","");
    }
    public NewsDetailSection(Object source, String title, String url, String content) {
        super(source,title,url,content);
        this.mTitle = title;
        this.mUrl = url;
        this.mContent = content;
    }

    @Override
    public NewsDetailPresenter onCreatePresenter() {
        return new NewsDetailPresenter(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initEvents() {
        super.initEvents();
        initData();
    }

    private void initData() {

        /*isShowDetailInfo(true);
        setDetailTitle(mTitle);
        setDetailTime("2018-9-10 15:38");
        load(mUrl,mContent);*/
    }


}
