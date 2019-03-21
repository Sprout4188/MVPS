package com.songcw.customer.util;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

import com.songcw.basecore.BaseApp;
import com.songcw.basecore.widget.nicetoast.Toasty;

public class TextUtil {

    /**
     * 添加"*"号后缀
     */
    public static Spanned suffixStar(String s) {
        String str = s + "<font color='#E7B24D'>*</font>";
        return Html.fromHtml(str);
    }

    /**
     * 检测TextView的内容是否为空
     *
     * @param tv  目标TextView
     * @param msg 内容为空时, Toast的内容
     * @return true 检测通过(即TextView有内容), false检测不通过(即TextView内容为空)
     */
    public static boolean check(TextView tv, String msg) {
        if (tv == null) return true;
        if (TextUtils.isEmpty(msg)) throw new IllegalArgumentException("Toast msg can not be null");
        if (TextUtils.isEmpty(tv.getText().toString().trim())) {
            Toasty.info(BaseApp.getContext(), msg);
            return false;
        }
        return true;
    }
}
