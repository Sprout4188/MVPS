package com.songcw.basecore.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * dp与px互转,sp与px互转的工具类
 *
 * Created by Sprout on 2016/11/29.
 */
public class DensityUtil {
    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float density = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * density + 0.5f);  //加0.5f是四舍五入
    }

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float density = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / density + 0.5f);  //加0.5f是四舍五入
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }


}
