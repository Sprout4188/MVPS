package com.jc.basecore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Sprout on 2018/8/23
 */
public class BitmapUtil {

    /**
     * 将Bitmap保存到File中
     */
    public static void bitmapToFile(Bitmap sourceBitmap, File file) {
        if (file.exists()) file.delete();
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            sourceBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置view radius
     */
    public static void setViewRadius(Context context, View view, int radius, int solidColor, int strokeColor, int strokeWidth){
        GradientDrawable drawable = new GradientDrawable();
        //设置drawable形状是矩形
        drawable.setShape(GradientDrawable.RECTANGLE);
        //设置圆角
        drawable.setCornerRadius(DensityUtil.dp2px(context, radius));
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth,strokeColor);
        //设置搜索框背景颜色
        view.setBackground(drawable);
    }
}
