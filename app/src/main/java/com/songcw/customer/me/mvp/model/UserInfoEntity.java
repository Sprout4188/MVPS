package com.songcw.customer.me.mvp.model;

import com.songcw.basecore.http.BaseBean;

import java.io.Serializable;

/**
 * Created by Sprout on 2018/8/29
 */
public class UserInfoEntity extends BaseBean {

    public UserData data;

    public static class UserData implements Serializable{
        private String token;//系统返回token

        private String id;//对象类型id

        private String userType;//类型 1-商户 2-员工 3-客户

        private String userNo;//登录人code

        private String mobile;//手机号

        private String userName;//用户昵称

        private String userLevel;//渠道商操作员等级，1：管理员 2：普通人员

        private String merchantNo;//商户code

        private String merchantName;//商户名称

        private String isActive; //1-激活 0-未激活 经纪人


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(String userLevel) {
            this.userLevel = userLevel;
        }

        public String getMerchantNo() {
            return merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }


        public String getIsActive() {
            return isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }


    }


    public final static String USERTYPE_STORE = "1";
    public final static String USERTYPE_ADMIN = "1";

    public final static String USERTYPE_AGENT = "2";
    public final static String USERTYPE_STAFF = "2";


}
