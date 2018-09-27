package com.songcw.customer.car_life.mvp.view;

import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.customer.R;

/**
 * @name sccustomer-v3.0
 * @class name：com.songcw.customer.car_life
 * @class describe
 * @anthor wjb
 * @time 2018/9/27 11:17
 * @change
 * @chang time
 * @class 车生活
 */
public class CarLifeFragment extends BaseFragment{

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_car_life;
    }

    @Override
    protected void addSections() {
        setTitle(getString(R.string.car_life));
        hideBack();
    }
}
