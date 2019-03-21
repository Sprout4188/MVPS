package com.songcw.customer.mvp.ui;

import com.songcw.customer.R;
import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.customer.mvp.section.MeSection;

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
        setTitle("颂车邦");
        hideBack();
        addSection(new MeSection(this));
    }
}
