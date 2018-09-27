package com.songcw.customer.home.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.section.AgentManagementSection;
import com.songcw.customer.main.mvp.view.FragmentShellActivity;

/**
 * @name employee
 * @class name：com.songcw.employee.home.activity
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 13:48
 * @change
 * @chang time
 * @class 经纪人管理 页面
 */
public class AgentManagementActivity extends BaseActivity{

    private static final int REQUEST_CODE_ADD_AGENT = 100;

    private AgentManagementSection mAgentManagementSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.agent_management));
        setTitleRightIcon(R.mipmap.ic_add);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_agent_management;
    }

    @Override
    protected void addSections() {
        mAgentManagementSection = new AgentManagementSection(this);
        addSection(mAgentManagementSection);
    }

    @Override
    public void onTitleRightIconClick() {
        super.onTitleRightIconClick();
        Intent intent = FragmentShellActivity.createIntent(this, AgentAddFragment.class, null);
        if (intent != null)
            startActivityForResult(intent,REQUEST_CODE_ADD_AGENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAgentManagementSection != null) {
            mAgentManagementSection.onActivityResult(requestCode,resultCode,data);
        }
    }
}
