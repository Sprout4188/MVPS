package com.songcw.basecore.sp;

import com.songcw.basecore.grobal.Config;
import com.songcw.basecore.sp.items.BooleanPrefItem;
import com.songcw.basecore.sp.items.StringPrefItem;

/**
 * Create by Sprout at 2017/8/15
 * 永久SP, 即退出登录时不会清空, 除非卸载
 */
public class PermanentInfoSP extends BaseSP {
    public static PermanentInfoSP instance = new PermanentInfoSP();

    private PermanentInfoSP() {
        super("songche_permanent_info"); //定义SP文件名
    }

    /**
     * 普通接口根地址
     */
    public static final StringPrefItem baseUrl = new StringPrefItem(instance, "baseUrl", Config.Http.Url_Sit);

    /**
     * 是否首次安装本APP
     */
    public static final BooleanPrefItem firstInstall = new BooleanPrefItem(instance, "firstInstall", true);
}
