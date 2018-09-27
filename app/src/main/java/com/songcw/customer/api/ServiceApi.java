package com.songcw.customer.api;

import com.songcw.basecore.http.StringBean;
import com.songcw.customer.home.mvp.model.AgentManagementBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @name employee
 * @class name：com.songcw.employee.home.api
 * @class describe
 * @anthor wjb
 * @time 2018/9/7 9:35
 * @change
 * @chang time
 * @class describe
 */
public interface ServiceApi {

    /**
     * 经纪人管理列表
     * @param map
     * @return
     */
    @POST("/openapi/merchants/merchant/agents")
    @FormUrlEncoded
    Observable<AgentManagementBean> agentsList(@FieldMap Map<String,String> map);

    /**
     * 新增经纪人
     * @param map
     * @return
     */
    @POST("/openapi/merchants/merchant/agents/agent/add")
    @FormUrlEncoded
    Observable<StringBean> agentAdd(@FieldMap Map<String,String> map);

}
