package com.songcw.customer.me.mvp.section;

import android.widget.TextView;

import com.songcw.basecore.mvp.BaseSection;
import com.songcw.customer.BuildConfig;
import com.songcw.customer.R;
import com.songcw.customer.me.mvp.presenter.VersionInfoPresenter;
import com.songcw.customer.me.mvp.view.VersionInfoView;

public class VersionInfoSection extends BaseSection<VersionInfoPresenter> implements VersionInfoView {

    public VersionInfoSection(Object source) {
        super(source);
    }

    @Override
    public VersionInfoPresenter onCreatePresenter() {
        return new VersionInfoPresenter(this);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {

        ((TextView) findView(R.id.txt_appVersion)).setText("版本号：V" + BuildConfig.VERSION_NAME);
    }
}