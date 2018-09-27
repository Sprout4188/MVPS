package com.songcw.customer.scan.mvp.view;

import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.customer.R;

/**
 * @name sccustomer-v3.0
 * @class name：com.songcw.customer.scan
 * @class describe
 * @anthor wjb
 * @time 2018/9/27 11:16
 * @change
 * @chang time
 * @class 扫码
 */
public class ScanFragment extends BaseFragment {
    @Override
    protected int setContentLayout() {
        return R.layout.fragment_scan;
    }

    @Override
    protected void addSections() {
        setTitle(getString(R.string.scan_code));
        hideBack();
    }
}
