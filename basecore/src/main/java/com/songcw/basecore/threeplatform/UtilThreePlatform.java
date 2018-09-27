package com.songcw.basecore.threeplatform;

import android.content.Context;

import com.songcw.basecore.util.AndroidUtil;
import com.songcw.basecore.util.NetUtil;

/**
 * Created by Sprout on 2018/8/21
 */
public class UtilThreePlatform implements ThreePlatform {
    @Override
    public void init(Context context) {
        AndroidUtil.initial(context);
        NetUtil.initial(context);
    }
}
