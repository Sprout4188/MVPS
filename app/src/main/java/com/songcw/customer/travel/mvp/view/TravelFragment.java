package com.songcw.customer.travel.mvp.view;

import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.customer.R;

/**
 * @name sccustomer-v3.0
 * @class name：com.songcw.customer.travel
 * @class describe
 * @anthor wjb
 * @time 2018/9/27 11:17
 * @change
 * @chang time
 * @class 出行
 */
public class TravelFragment extends BaseFragment {

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_trael;
    }

    @Override
    protected void addSections() {
        setTitle(getString(R.string.sw_travel));
        hideBack();
    }
}
