package com.jc.efire.mvp.presenter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jc.basecore.http.ICallBack;
import com.jc.efire.mvp.model.BaseListEntity;
import com.jc.efire.mvp.view.BaseListView;

import java.util.Map;

/**
 * Created by Sprout on 2018/10/24
 */
public abstract class BaseListSearchPresent<T, A extends BaseQuickAdapter, V extends BaseListView> extends BaseListPresent<T, A, V> {
    public BaseListSearchPresent(V view) {
        super(view);
    }

    /**
     * 发起搜索(默认实现: 搜索订单, 子类可重写)
     */
    public void search(Map<String, String> map, ICallBack<BaseListEntity<T>> callBack) {

    }
}
