package com.jc.efire.mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jc.efire.mvp.ui.FunFragment;
import com.jc.efire.mvp.ui.HomeFragment;
import com.jc.efire.mvp.ui.MeFragment;
import com.jc.efire.mvp.ui.MsgFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        HomeFragment homeFragment = new HomeFragment();
        FunFragment funFragment = new FunFragment();
        MsgFragment msgFragment = new MsgFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(funFragment);
        fragments.add(msgFragment);
        fragments.add(meFragment);
    }

    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public int getCount() {
        return fragments.size();
    }
}
