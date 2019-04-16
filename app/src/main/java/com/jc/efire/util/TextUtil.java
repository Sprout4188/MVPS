package com.jc.efire.util;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

import com.jc.basecore.BaseApp;
import com.jc.basecore.widget.nicetoast.Toasty;

public class TextUtil {
    public static Spanned suffixStar(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append("<font color='#E7B24D'>*</font>");
        return Html.fromHtml(sb.toString());
    }

    public static boolean check(TextView tv, String msg) {
        if (tv == null) {
            return true;
        }
        if (TextUtils.isEmpty(msg)) {
            throw new IllegalArgumentException("Toast msg can not be null");
        } else if (!TextUtils.isEmpty(tv.getText().toString().trim())) {
            return true;
        } else {
            Toasty.info(BaseApp.getContext(), msg);
            return false;
        }
    }
}
