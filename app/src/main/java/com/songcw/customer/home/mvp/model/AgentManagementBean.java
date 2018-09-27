package com.songcw.customer.home.mvp.model;

import com.songcw.basecore.http.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @name employee
 * @class name：com.songcw.model.home
 * @class describe
 * @anthor wjb
 * @time 2018/9/11 13:49
 * @change
 * @chang time
 * @class describe
 */
public class AgentManagementBean extends BaseBean{

    public List<Data> data;

    public class Data implements Serializable {

        /**
         * age : null
         * contactAddress : null
         * createUser : 00193
         * eduLevel : null
         * gmtCreate : 1536822840000
         * gmtModified : 1536822840000
         * homeAddress : null
         * idCardNo : null
         * isActive : 0
         * isInit : 1
         * isPreset : 1
         * job : null
         * loginName : null
         * loginPassword : 5f9c50b9d370e553b076ecf20870baab6dff1d061fb15868b62ca17f04b70a16
         * marryInfo : null
         * merchantName : 测试
         * merchantNo : 120001
         * modifiedUser : null
         * qrCodeUrl : http://dev-songchedai.oss-cn-shanghai.aliyuncs.com/merchant/201809/1536822839032.jpg
         * sex : null
         * useStatus : 1
         * userEmail : null
         * userId : 24
         * userLevel : 2
         * userMobile : 18215644525
         * userName : 测试人员
         * userNo : 1200010023
         */

        public String age;
        public String contactAddress;
        public String createUser;
        public String eduLevel;
        public long gmtCreate;
        public long gmtModified;
        public String homeAddress;
        public String idCardNo;
        public String isActive;
        public String isInit;
        public String isPreset;
        public String job;
        public String loginName;
        public String loginPassword;
        public String marryInfo;
        public String merchantName;
        public String merchantNo;
        public String modifiedUser;
        public String qrCodeUrl;
        public String sex;
        public String useStatus;
        public String userEmail;
        public String userId;
        public String userLevel;
        public String userMobile;
        public String userName;
        public String userNo;

        public boolean isActivated;//是否已已激活

    }
   
}
