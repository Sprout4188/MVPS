package com.jc.efire.mvp.section;

import com.flyco.tablayout.CommonTabLayout;
import com.jc.basecore.mvp.BaseSection;
import com.jc.basecore.widget.NoScrollViewPager;
import com.jc.efire.R;
import com.jc.efire.mvp.presenter.MainContentPresenter;

import butterknife.BindView;

public class MainContentSection extends BaseSection<MainContentPresenter> {
    @BindView(R.id.tab_layout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_content)
    NoScrollViewPager mViewPager;

    public MainContentSection(Object source) {
        super(source);
    }

    public MainContentPresenter onCreatePresenter() {
        return new MainContentPresenter(this);
    }

    public void init() {
        mPresenter.initTab(mTabLayout, mViewPager);
    }

    public NoScrollViewPager getViewPager() {
        return mViewPager;
    }
}
