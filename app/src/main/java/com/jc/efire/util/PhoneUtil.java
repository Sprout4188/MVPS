package com.jc.efire.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.regex.Pattern;

public class PhoneUtil {
    /**
     * @param isDirectly true直接拨打，false跳转到系统拨号界面
     */
    public static void callPhone(Context context, String phoneNum, boolean isDirectly) {
        Intent intent = new Intent();
        if (isDirectly) intent.setAction("android.intent.action.CALL");
        else intent.setAction("android.intent.action.DIAL");
        StringBuilder sb = new StringBuilder();
        sb.append("tel:");
        sb.append(phoneNum);
        intent.setData(Uri.parse(sb.toString()));
        context.startActivity(intent);
    }

    public static boolean isMobileNumber(String value) {
        return value != null && value.length() == 11 && isNumeric(value);
    }

    public static boolean isNumeric(String value) {
        return Pattern.compile("[0-9]+").matcher(value).matches();
    }
}
