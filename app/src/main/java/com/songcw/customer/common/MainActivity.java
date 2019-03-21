package com.songcw.customer.common;

import android.content.Intent;
import android.os.Bundle;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.orhanobut.logger.Logger;
import com.songcw.basecore.event.JumpConfigBaseUrlEvent;
import com.songcw.basecore.event.NetworkStatusChangeEvent;
import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.basecore.ui.ConfigActivity;
import com.songcw.basecore.util.ActivityUtil;
import com.songcw.basecore.widget.nicetoast.Toasty;
import com.songcw.customer.BuildConfig;
import com.songcw.customer.R;
import com.songcw.customer.mvp.section.BottomNavigationTabSection;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void addSections() {
        addSection(new BottomNavigationTabSection(this));
    }

    @Subscribe()    //from = NetUtil, 即网络环境变化时会发送event
    public void onNetChange(NetworkStatusChangeEvent event) {
        Logger.t(TAG).d("网络环境改变了");
    }

    @Subscribe()    //from = BaseActivity, 即每个界面的标题800ms内连续点击3次会发送event
    public void onJumpConfigBaseUrl(JumpConfigBaseUrlEvent event) {
        if (BuildConfig.DEBUG) {
            Intent intent = new Intent(this, ConfigActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (ActivityUtil.isOverWhenPressAgain(2000))
            Toasty.normal(this, getString(R.string.exit_press_again));
        else super.onBackPressed();
    }
}
