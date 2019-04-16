package com.jc.efire.mvp.section;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.jc.basecore.event.JumpConfigBaseUrlEvent;
import com.jc.basecore.event.NetworkStatusChangeEvent;
import com.jc.basecore.mvp.BasePresenter;
import com.jc.basecore.mvp.BaseSection;
import com.jc.basecore.mvp.IController;
import com.jc.basecore.ui.ConfigActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by Sprout on 2018/12/11
 */
public class MainEventReceiveSection extends BaseSection<BasePresenter> implements IController.IView {
    public MainEventReceiveSection(Object source) {
        super(source);
    }

    @Override
    public BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Subscribe()    //from = NetUtil, 即网络环境变化时会发送event
    public void onNetChange(NetworkStatusChangeEvent event) {
        Logger.t(TAG).d("网络环境改变了");
    }

    @Subscribe()    //from = BaseActivity, 即每个界面的标题800ms内连续点击3次会发送event
    public void onJumpConfigBaseUrl(JumpConfigBaseUrlEvent event) {
        //TODO 上线前打开
//        if (BuildConfig.DEBUG)
        startActivity(ConfigActivity.class);
    }
}
