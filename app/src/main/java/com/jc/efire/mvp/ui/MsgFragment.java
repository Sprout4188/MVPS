package com.jc.efire.mvp.ui;

import com.jc.basecore.mvp.BaseFragment;
import com.jc.efire.R;

public class MsgFragment extends BaseFragment {
    public int setContentLayout() {
        return R.layout.fragment_msg;
    }

    public void addSections() {
        setTitle("消息中心");
    }
}
