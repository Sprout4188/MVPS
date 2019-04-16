package com.jc.basecore.threeplatform;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sprout on 2018/8/21
 */
public class ThreePlatformManager {
    private List<ThreePlatform> list;
    private Context context;

    public ThreePlatformManager(Context context) {
        this.context = context;
    }

    public ThreePlatformManager addThreePlatform(ThreePlatform threePlatform) {
        if (list == null) list = new ArrayList<>();
        list.add(threePlatform);
        return this;
    }

    public void init() {
        if (list != null && context != null) {
            for (ThreePlatform threePlatform : list) {
                threePlatform.init(context);
            }
        }
    }
}
