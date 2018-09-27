package com.songcw.customer.home.mvp.presenter;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.songcw.customer.R;
import com.songcw.customer.car_life.mvp.view.CarLifeFragment;
import com.songcw.customer.home.mvp.view.HomeFragment;
import com.songcw.customer.me.mvp.view.MeFragment;
import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.basecore.mvp.IController;
import com.songcw.customer.scan.mvp.view.ScanFragment;
import com.songcw.customer.travel.mvp.view.TravelFragment;

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
                        .title(getContext().getString(R.string.home)).build());
        models.add(
                new NavigationTabBar.Model.Builder(resources.getDrawable(R.mipmap.ic_tab_home_unselect), Color.WHITE)
                        .selectedIcon(resources.getDrawable(R.mipmap.ic_tab_home_select))
                        .title(getContext().getString(R.string.sw_travel).substring(2,4)).build());
        models.add(
                new NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.mipmap.ic_tab_workbench_unselect), Color.WHITE)
                        .selectedIcon(resources.getDrawable(R.mipmap.ic_tab_workbench_select))
                        .title(getContext().getString(R.string.scan_code)).build());
        models.add(
                new NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.mipmap.ic_tab_me_unselect), Color.WHITE)
                        .selectedIcon(resources.getDrawable(R.mipmap.ic_tab_me_select))
                        .title(getContext().getString(R.string.car_life)).build());
        models.add(
                new NavigationTabBar.Model.Builder(
                        resources.getDrawable(R.mipmap.ic_tab_me_unselect), Color.WHITE)
                        .selectedIcon(resources.getDrawable(R.mipmap.ic_tab_me_select))
                        .title(getContext().getString(R.string.me)).build());
        return models;
    }

    /**
     * 获取四大模块Fragment
     */
    @NonNull
    public List<BaseFragment> getMainViewPagerFragments() {
        List<BaseFragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        TravelFragment travelFragment = new TravelFragment();
        ScanFragment scanFragment = new ScanFragment();
        CarLifeFragment carLifeFragment = new CarLifeFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(travelFragment);
        fragments.add(scanFragment);
        fragments.add(carLifeFragment);
        fragments.add(meFragment);
        return fragments;
    }
}
