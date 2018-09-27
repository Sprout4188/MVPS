package com.songcw.customer.home.mvp.presenter;

import com.songcw.basecore.http.ICallBack;
import com.songcw.basecore.http.RetrofitUtil;
import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.customer.api.ServiceApi;
import com.songcw.customer.home.mvp.section.AgentManagementView;
import com.songcw.customer.application.Constant;
import com.songcw.customer.home.mvp.model.AgentManagementBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.presenter
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 13:49
 * @change
 * @chang time
 * @class describe
 */
public class AgentManagementPresenter extends BasePresenter<AgentManagementView>{

    public AgentManagementPresenter(AgentManagementView view) {
        super(view);
    }

    public void getAgentsList(int currentPage, int pageSize, final boolean isLoadMore){

        Map<String,String> map = new HashMap<>();
        map.put(Constant.HttpParams.CURRENT_PAGE,currentPage+"");
        map.put(Constant.HttpParams.PAGE_SIZE,pageSize+"");
        addDisposable(RetrofitUtil.create(ServiceApi.class).agentsList(map), new ICallBack<AgentManagementBean>() {

            @Override
            public void onSuccess(AgentManagementBean bean) {
                mView.getPageDataSucc(bean,isLoadMore);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.getPageDataFail(errorMsg,isLoadMore);
            }
        });

    }
}
