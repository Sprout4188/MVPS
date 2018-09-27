package com.songcw.customer.home.mvp.view;

import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.section.AgentAddSection;

/**
 * @name employee
 * @class name：com.songcw.employee.home.fragment
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 15:19
 * @change
 * @chang time
 * @class 经纪人添加
 */
public class AgentAddFragment extends BaseFragment{


    @Override
    protected int setContentLayout() {
        return R.layout.fragment_agent_add;
    }

    @Override
    protected void addSections() {
        setTitle(getString(R.string.add));
        addSection(new AgentAddSection(this));
    }

}
