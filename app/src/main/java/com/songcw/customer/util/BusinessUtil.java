package com.songcw.customer.util;

import android.content.Context;

import com.songcw.basecore.cache.ACache;
import com.songcw.customer.me.mvp.model.UserInfoEntity;
import com.songcw.customer.application.Constant;

/**
 * @name employee
 * @class name：com.songcw.customer.util
 * @class describe
 * @anthor wjb
 * @time 2018/9/26 14:48
 * @change
 * @chang time
 * @class describe
 */
public class BusinessUtil {

    /**
     * 登录
     *************************************************************************************/
    public static void saveLoginData(Context ctx, UserInfoEntity.UserData userData) {
        if (ctx == null || userData == null)
            return;
        ACache ac = ACache.get(ctx);
        ac.put(Constant.CacheKey.USER_INFO, userData);
    }

    public static void removeLoginData(Context ctx) {
        if (ctx == null)
            return;
        ACache ac = ACache.get(ctx);
        ac.remove(Constant.CacheKey.USER_INFO);
    }

    public static UserInfoEntity.UserData getLoginData(Context ctx) {
        UserInfoEntity.UserData lrBean = getLoginDataInternal(ctx);
        if (lrBean == null)
            return new UserInfoEntity.UserData();
        return lrBean;
    }

    private static UserInfoEntity.UserData getLoginDataInternal(Context ctx) {
        if (ctx == null)
            return null;
        ACache ac = ACache.get(ctx);
        return (UserInfoEntity.UserData) ac.getAsObject(Constant.CacheKey.USER_INFO);
    }

    public static boolean hasLoginData(Context ctx) {
        return getLoginDataInternal(ctx) != null;
    }
}
