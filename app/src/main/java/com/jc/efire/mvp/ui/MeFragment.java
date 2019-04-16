package com.jc.efire.mvp.ui;

import com.jc.basecore.mvp.BaseFragment;
import com.jc.efire.R;

/**
 * Created by Sprout on 2018/7/30
 */

public class MeFragment extends BaseFragment {

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void addSections() {
        setTitle("我的");
    }
}
