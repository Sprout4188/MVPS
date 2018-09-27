package com.songcw.basecore.sp;

import com.songcw.basecore.sp.items.StringPrefItem;

/**
 * Create by Sprout at 2017/8/15
 * 会员SP, 退出登录时清空
 */
public class MemberInfoSP extends BaseSP {
    public static MemberInfoSP instance = new MemberInfoSP();

    private MemberInfoSP() {
        super("songche_member_info"); //定义SP文件名
    }

    /**
     * 设备唯一编码uuid
     */
    public static final StringPrefItem uuid = new StringPrefItem(instance, "uuid", "");


    /**
     * token
     */
    public static final StringPrefItem token = new StringPrefItem(instance, "token", "");


    /**
     * id
     */

    public static final StringPrefItem id = new StringPrefItem(instance,"id","");


    /**
     * userType //类型 1-商户 2-员工 3-客户
     */

    public static final StringPrefItem userType = new StringPrefItem(instance,"userType","");


    /**
     * userNo //登录人code
     */

    public static final StringPrefItem userNo = new StringPrefItem(instance,"userNo","");


    /**
     * mobile //手机号
     */

    public static final StringPrefItem mobile = new StringPrefItem(instance,"mobile","");



    /**
     * userName //用户昵称
     */

    public static final StringPrefItem userName = new StringPrefItem(instance,"userName","");


    /**
     * userLevel //渠道商操作员等级，1：管理员 2：普通人员
     */

    public static final StringPrefItem userLevel = new StringPrefItem(instance,"userLevel","");


    /**
     * merchantNo //商户code
     */

    public static final StringPrefItem merchantNo = new StringPrefItem(instance,"merchantNo","");


    /**
     * merchantName //商户名称
     */

    public static final StringPrefItem merchantName = new StringPrefItem(instance,"merchantName","");



    @Override
    public void clear() {
        super.clear();
        token.setValue("");
        id.setValue("");
        userType.setValue("");
        userNo.setValue("");
        mobile.setValue("");
        userName.setValue("");
        userLevel.setValue("");
        merchantNo.setValue("");
        merchantName.setValue("");
    }
}
