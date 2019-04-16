package com.jc.basecore.sp;

import com.jc.basecore.sp.items.StringPrefItem;

/**
 * Create by Sprout at 2017/8/15
 * 会员SP, 退出登录时清空
 */
public class MemberInfoSP extends BaseSP {
    public static MemberInfoSP instance = new MemberInfoSP();

    private MemberInfoSP() {
        super("songche_member_info"); //定义SP文件名
    }

    public static final StringPrefItem uuid = new StringPrefItem(instance, "uuid", "");

    public static final StringPrefItem token = new StringPrefItem(instance, "token", "");

    @Override
    public void clear() {
        super.clear();
        token.setValue("");
    }
}
