package com.songcw.customer.me.mvp.section;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.songcw.basecore.mvp.BaseSection;
import com.songcw.customer.R;
import com.songcw.customer.me.mvp.presenter.MePresenter;
import com.songcw.customer.me.mvp.view.MeView;
import com.songcw.customer.me.mvp.view.VersionInfoActivity;

public class MeSection extends BaseSection<MePresenter> implements MeView, View.OnClickListener {

    private ImageView imgHead;
    private TextView txtUserName;
    private LinearLayout layoutUserInfo;
    private LinearLayout layoutChangePsd;
    private LinearLayout layoutVersionInfo;

    public MeSection(Object source) {
        super(source);
    }

    @Override
    public MePresenter onCreatePresenter() {
        return new MePresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v == layoutChangePsd) {

        } else if (v == layoutUserInfo) {


        } else if (v == layoutVersionInfo) {

            startActivity(VersionInfoActivity.class);


        }
    }

    @Override
    protected void initEvents() {
        layoutChangePsd.setOnClickListener(this);
        layoutUserInfo.setOnClickListener(this);
        layoutVersionInfo.setOnClickListener(this);

    }

    @Override
    protected void initViews() {

        imgHead = ((ImageView) findView(R.id.img_head));

        txtUserName = ((TextView) findView(R.id.txt_userName));

        layoutUserInfo = ((LinearLayout) findView(R.id.layout_userInfo));

        layoutChangePsd = ((LinearLayout) findView(R.id.layout_changePsd));

        layoutVersionInfo = ((LinearLayout) findView(R.id.layout_versionInfo));


    }
}