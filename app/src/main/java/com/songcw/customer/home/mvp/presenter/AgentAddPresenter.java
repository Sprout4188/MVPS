package com.songcw.customer.home.mvp.presenter;

import com.songcw.basecore.http.ICallBack;
import com.songcw.basecore.http.RetrofitUtil;
import com.songcw.basecore.http.StringBean;
import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.customer.api.ServiceApi;
import com.songcw.customer.home.mvp.section.AgentAddView;
import com.songcw.customer.application.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.presenter
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 19:05
 * @change
 * @chang time
 * @class describe
 */
public class AgentAddPresenter extends BasePresenter<AgentAddView>{

    public AgentAddPresenter(AgentAddView view) {
        super(view);
    }

    public void addAgent(String name, String mobile){
        Map<String,String> map = new HashMap<>();
        map.put(Constant.HttpParams.NAME,name);
        map.put(Constant.HttpParams.MOBILE,mobile);
        addDisposable(RetrofitUtil.create(ServiceApi.class).agentAdd(map), new ICallBack<StringBean>() {

            @Override
            public void onSuccess(StringBean entity) {
                mView.onSuccess(entity);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.onFailure(errorMsg);
            }
        });
    }
}
