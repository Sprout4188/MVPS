package com.songcw.customer.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.songcw.basecore.mvp.BaseFragment;

import java.util.List;

/**
 * Created by Sprout on 2018/7/30
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if (fragments == null || fragments.size() == 0) throw new IllegalArgumentException("fragments can not be null");
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
