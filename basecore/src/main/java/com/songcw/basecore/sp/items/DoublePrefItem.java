package com.songcw.basecore.sp.items;

import com.songcw.basecore.sp.BaseSP;

/**
 * Create by Sprout at 2017/8/15
 */
public class DoublePrefItem extends BasePrefItem<Double> {

    public DoublePrefItem(BaseSP sp, String key, Double defaultValue) {
        super(sp, key, defaultValue);
    }

    @Override
    public Double getValue() {
        return sp.getDoubleItem(key, value);
    }

    @Override
    public void setValue(Double value) {
        sp.setDoubleItem(key, value);
    }
}
