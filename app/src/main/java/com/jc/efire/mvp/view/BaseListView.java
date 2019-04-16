package com.jc.efire.mvp.view;

import com.jc.basecore.mvp.IController.IView;
import com.jc.efire.mvp.model.BaseListEntity;
import java.util.Map;

public interface BaseListView extends IView {
    void getPageDataFail(String str, int i);

    void getPageDataSucc(BaseListEntity baseListEntity, int i);

    void putExtras(Map<String, String> map);
}
