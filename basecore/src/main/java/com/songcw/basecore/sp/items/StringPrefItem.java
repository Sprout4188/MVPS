package com.songcw.basecore.sp.items;

import com.songcw.basecore.sp.BaseSP;

/**
 * Create by Sprout at 2017/8/15
 */
public class StringPrefItem extends BasePrefItem<String> {

    public StringPrefItem(BaseSP sp, String key, String defaultValue) {
        super(sp, key, defaultValue);
    }

    @Override
    public String getValue() {
        return sp.getStringItem(key, value);
    }

    @Override
    public void setValue(String value) {
        sp.setStringItem(key, value);
    }
}
