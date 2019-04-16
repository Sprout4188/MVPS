package com.jc.efire.mvp.model;

import com.jc.basecore.http.BaseBean;
import java.util.List;

public class BaseListEntity<T> extends BaseBean {
    public List<T> data;
}
