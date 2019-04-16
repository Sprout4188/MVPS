package com.jc.basecore.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.jc.basecore.BaseApp;

import java.io.FileNotFoundException;

/**
 * Created by Sprout on 2018/9/11
 */
public class UriUtil {


    /**
     * 将Uri转换成Bitmap
     */
    public static Bitmap uriToBitmap(Uri sourceUri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(BaseApp.getContext().getContentResolver().openInputStream(sourceUri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     */
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {  // DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {      // MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) { // MediaStore (and general)
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {  // File
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isImage(Uri uri) {
        if (uri == null) return false;
        String path = uri.toString();
        try {
            path = UriUtil.getPath(BaseApp.getContext(), uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (TextUtils.isEmpty(path)) return false;
        return path.contains("/images/")
                || path.endsWith(".jpg")
                || path.endsWith(".jpeg")
                || path.endsWith(".png")
                || path.endsWith(".bmp")
                || path.endsWith(".webp");
    }

    public static boolean isVideo(Uri uri) {
        if (uri == null) return false;
        String path = uri.toString();
        try {
            path = UriUtil.getPath(BaseApp.getContext(), uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (TextUtils.isEmpty(path)) return false;
        return path.contains("/video/")
                || path.endsWith(".mpeg")
                || path.endsWith(".mpg")
                || path.endsWith(".mp4")
                || path.endsWith(".m4v")
                || path.endsWith(".mov")
                || path.endsWith(".3gp")
                || path.endsWith(".3gpp")
                || path.endsWith(".3g2")
                || path.endsWith(".3gpp2")
                || path.endsWith(".mkv")
                || path.endsWith(".webm")
                || path.endsWith(".ts")
                || path.endsWith(".avi");
    }

    public static boolean isRes(Uri uri) {
        if (uri == null) return false;
        return uri.toString().startsWith("android.resource://");
    }

    /**
     * 从资源文件中创建Uri
     */
    public static Uri createFromRes(Context context, int resId) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + resId);
    }
}
