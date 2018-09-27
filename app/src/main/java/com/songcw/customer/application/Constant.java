package com.songcw.customer.application;

/**
 * @name employee
 * @class name：com.songcw.customer.constant
 * @class describe
 * @anthor wjb
 * @time 2018/9/4 16:44
 * @change
 * @chang time
 * @class 常量
 */
public interface Constant {
    //首页相关tag 常量
    interface HomeConstant {
        //快速催收
        String TAG_QUICK_COLLECTION = "quick_collection";
        //经纪人管理
        String TAG_AGENT_MANAGEMENT = "agent_management";
        //还款客户管理
        String TAG_REPAYMENT_CUSTOMER_MANAGEMENT = "repayment_customer_management";
        //逾期客户管理
        String TAG_OVERDUE_CUSTOMER_MANAGEMENT = "overdue_customer_management";

        //用户角色
        //渠道商\经纪人
        String CHANNEL_PROVIDER = "1";
        //经纪人
        String AGENT = "agent";
        //员工
        String EMPLOYEE = "2";
    }

    interface ParamName {
        String title = "title";
        String url = "url";
        String content = "content";
    }

    interface HttpParams {
        String CURRENT_PAGE = "currentPage";
        String PAGE_SIZE = "pageSize";
        String MOBILE = "mobile";
        String NAME = "name";
    }

    interface CacheKey {
        String USER_INFO = "user_info";
    }
}
