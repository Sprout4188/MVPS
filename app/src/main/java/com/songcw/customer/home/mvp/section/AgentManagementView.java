package com.songcw.customer.home.mvp.section;

import com.songcw.basecore.mvp.IController;
import com.songcw.customer.home.mvp.model.AgentManagementBean;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.section
 * @class describe
 * @anthor wjb
 * @time 2018/9/13 15:25
 * @change
 * @chang time
 * @class describe
 */
public interface AgentManagementView extends IController.IView{

    void getPageDataSucc(AgentManagementBean datas, boolean isLoadMore);

    void getPageDataFail(String message, boolean isLoadMore);
}
