package com.songcw.customer.mvp.ui;

import com.songcw.customer.R;
import com.songcw.customer.mvp.section.HomeSection;
import com.songcw.basecore.mvp.BaseFragment;

/**
 * Created by Sprout on 2018/7/30
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void addSections() {
        addSection(new HomeSection(this));
    }
}
