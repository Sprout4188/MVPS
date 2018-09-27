package com.songcw.customer.home.mvp.section;

import com.songcw.basecore.http.StringBean;
import com.songcw.basecore.mvp.IController;

/**
 * @name employee
 * @class name：com.songcw.employee.home.mvp.section
 * @class describe
 * @anthor wjb
 * @time 2018/9/13 17:19
 * @change
 * @chang time
 * @class describe
 */
public interface AgentAddView extends IController.IView{

    void onSuccess(StringBean bean);
    void onFailure(String message);
}
