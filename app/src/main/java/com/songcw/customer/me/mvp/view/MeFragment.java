package com.songcw.customer.me.mvp.view;

import com.songcw.customer.R;
import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.customer.me.mvp.section.MeSection;

/**
 * Created by Sprout on 2018/7/30
 * 我的模块
 */

public class MeFragment extends BaseFragment {

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void addSections() {

        setTitle("颂车邦");
        hideBack();
        addSection(new MeSection(this));
    }
}
