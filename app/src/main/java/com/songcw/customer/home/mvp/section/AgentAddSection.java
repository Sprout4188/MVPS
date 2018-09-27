package com.songcw.customer.home.mvp.section;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.songcw.basecore.http.StringBean;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.widget.nicetoast.Toasty;
import com.songcw.customer.R;
import com.songcw.customer.home.mvp.presenter.AgentAddPresenter;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.section
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 19:02
 * @change
 * @chang time
 * @class 经纪人添加
 */
public class AgentAddSection extends BaseSection<AgentAddPresenter> implements View.OnClickListener,AgentAddView {

    private EditText mEtAgentName;
    private EditText mEtAgentPhone;
    private TextView mTvSubmit;

    public AgentAddSection(Object source) {
        super(source);
    }

    @Override
    public AgentAddPresenter onCreatePresenter() {
        return new AgentAddPresenter(this);
    }


    @Override
    protected void initViews() {

        mEtAgentName = (EditText) findView(R.id.et_agent_name);
        mEtAgentPhone = (EditText) findView(R.id.et_agent_phone);
        mTvSubmit = (TextView) findView(R.id.tv_submit);
    }

    @Override
    protected void initEvents() {

        mTvSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_submit:
                showLoading();
                mPresenter.addAgent(mEtAgentName.getText().toString().trim(),mEtAgentPhone.getText().toString().trim());
                break;
        }
    }

    @Override
    public void onSuccess(StringBean bean) {
        hideLoading();
        Toasty.normal(getContext(),bean.message);
        finish();
    }

    @Override
    public void onFailure(String message) {
        hideLoading();
        Toasty.normal(getContext(),message);
    }
}
