package com.jc.efire.mvp.presenter;

import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jc.basecore.mvp.BasePresenter;
import com.jc.basecore.mvp.IController.IView;
import com.jc.basecore.widget.NoScrollViewPager;
import com.jc.efire.R;
import com.jc.efire.common.MainActivity;
import com.jc.efire.mvp.adapter.MainFragmentAdapter;
import com.jc.efire.mvp.model.TabEntity;

import java.util.ArrayList;

public class MainContentPresenter extends BasePresenter {
    private int[] mIconSelectIds = new int[]{R.drawable.ic_tab_home_select, R.drawable.ic_tab_function_select, R.drawable.ic_tab_msg_select, R.drawable.ic_tab_me_select};
    private int[] mIconUnselectIds = new int[]{R.drawable.ic_tab_home_unselect, R.drawable.ic_tab_function_unselect, R.drawable.ic_tab_msg_unselect, R.drawable.ic_tab_me_unselect};
    private String[] mTitles = new String[]{"首页", "功能", "消息", "我的"};

    public MainContentPresenter(IView view) {
        super(view);
    }

    public void initTab(final CommonTabLayout mTabLayout, final NoScrollViewPager mViewPager) {
        mTabLayout.setTabData(getTabEntities());
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            public void onTabReselect(int position) {
            }
        });
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setAdapter(new MainFragmentAdapter(((MainActivity) getContext()).getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(3);
    }

    private ArrayList<CustomTabEntity> getTabEntities() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        return mTabEntities;
    }
}
