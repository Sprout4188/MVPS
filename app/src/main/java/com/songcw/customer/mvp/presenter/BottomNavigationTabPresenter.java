package com.songcw.customer.mvp.presenter;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.basecore.mvp.IController;
import com.songcw.customer.R;
import com.songcw.customer.mvp.ui.HomeFragment;
import com.songcw.customer.mvp.ui.MeFragment;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Sprout on 2018/8/28
 */
public class BottomNavigationTabPresenter extends BasePresenter {

    public BottomNavigationTabPresenter(IController.IView view) {
        super(view);
    }

    @NonNull
    public ArrayList<NavigationTabBar.Model> getModels() {
        Resources resources = getContext().getResources();
        ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(resources.getDrawable(R.mipmap.ic_tab_home_unselect), Color.WHITE)
                        .selectedIcon(resources.getDrawable(R.mipmap.ic_tab_home_select))
                        .title("首页").build());
        models.add(
                new NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.mipmap.ic_tab_me_unselect), Color.WHITE)
                        .selectedIcon(resources.getDrawable(R.mipmap.ic_tab_me_select))
                        .title("我的").build());
        return models;
    }

    /**
     * 获取四大模块Fragment
     */
    @NonNull
    public List<BaseFragment> getMainViewPagerFragments() {
        List<BaseFragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(meFragment);
        return fragments;
    }
}
