package com.songcw.customer.mvp.section;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.customer.R;
import com.songcw.customer.common.MainViewPagerAdapter;
import com.songcw.customer.mvp.presenter.BottomNavigationTabPresenter;
import com.songcw.basecore.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Sprout on 2018/8/28
 * 底部导航栏切片
 */
public class BottomNavigationTabSection extends BaseSection<BottomNavigationTabPresenter> {
    NoScrollViewPager vpMain;
    NavigationTabBar ntbMain;

    public BottomNavigationTabSection(Object source) {
        super(source);
    }

    @Override
    public BottomNavigationTabPresenter onCreatePresenter() {
        return new BottomNavigationTabPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @Override
    protected void initViews() {
        vpMain = (NoScrollViewPager) findView(R.id.vp_main);
        ntbMain = (NavigationTabBar) findView(R.id.ntb_main);
    }

    @Override
    protected void init() {

    }

    private void initUI() {
        List<BaseFragment> fragments = mPresenter.getMainViewPagerFragments();
        vpMain.setOffscreenPageLimit(2);
        vpMain.setAdapter(new MainViewPagerAdapter(((BaseActivity) getContext()).getSupportFragmentManager(), fragments));
        ArrayList<NavigationTabBar.Model> models = mPresenter.getModels();
        ntbMain.setModels(models);
        ntbMain.setViewPager(vpMain, 0);
        ntbMain.setIsSwiped(false);
    }
}
