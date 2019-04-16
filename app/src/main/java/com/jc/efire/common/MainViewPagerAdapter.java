package com.jc.efire.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jc.basecore.mvp.BaseFragment;

import java.util.List;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if (fragments == null || fragments.size() == 0) {
            throw new IllegalArgumentException("fragments can not be null");
        }
        this.fragments = fragments;
    }

    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public int getCount() {
        return fragments.size();
    }
}
