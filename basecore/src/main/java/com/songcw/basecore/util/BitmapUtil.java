package com.songcw.basecore.util;

import android.graphics.Bitmap;

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
}
