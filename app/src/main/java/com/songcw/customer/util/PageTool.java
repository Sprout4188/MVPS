package com.songcw.customer.util;

import android.content.Context;
import android.content.Intent;

import com.songcw.basecore.ui.ConfigActivity;
import com.songcw.customer.application.Constant;
import com.songcw.customer.home.mvp.view.NewsDetailActivity;

/**
 * @name employee
 * @class name：com.songcw.employee.home.tool
 * @class describe
 * @anthor wjb
 * @time 2018/9/10 17:19
 * @change
 * @chang time
 * @class describe
 */
public class PageTool {

    /**
     * 去 新闻/头条/Banner/广告 详情页面
     *
     * @param context
     * @param title
     * @param url
     * @param content
     */
    public static void toNewsDetailActivity(Context context, String title, String url, String content) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constant.ParamName.title, title);
        intent.putExtra(Constant.ParamName.url, url);
        intent.putExtra(Constant.ParamName.content, content);
        context.startActivity(intent);
    }

    /**
     * 去 URL配置界面
     */
    public static void toConfigActivity(Context context) {
        Intent intent = new Intent(context, ConfigActivity.class);
        context.startActivity(intent);
    }
}
