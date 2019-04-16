package com.jc.basecore.widget.nicetoast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jc.basecore.R;


/**
 * This file is part of Toasty.
 * <p>
 * Toasty is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Toasty is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Toasty.  If not, see <http://www.gnu.org/licenses/>.
 */

@SuppressLint("InflateParams")
public class Toasty {

    private static final @ColorInt
    int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    private static final @ColorInt
    int ERROR_COLOR = Color.parseColor("#D50000");
    private static final @ColorInt
    int INFO_COLOR = Color.parseColor("#3F51B5");
    private static final @ColorInt
    int SUCCESS_COLOR = Color.parseColor("#388E3C");
    private static final @ColorInt
    int WARNING_COLOR = Color.parseColor("#FFA900");

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";
    private static Toast currentToast;

    private Toasty() {
    }

    public static void normal(@NonNull Context context, @NonNull CharSequence message) {
        normal(context, message, Toast.LENGTH_SHORT, null, false);
    }

    public static void normal(@NonNull Context context, @NonNull CharSequence message, Drawable icon) {
        normal(context, message, Toast.LENGTH_SHORT, icon, true);
    }

    public static void normal(@NonNull Context context, @NonNull CharSequence message, int duration) {
        normal(context, message, duration, null, false);
    }

    public static void normal(@NonNull Context context, @NonNull CharSequence message, int duration, Drawable icon) {
        normal(context, message, duration, icon, true);
    }

    public static void normal(@NonNull Context context, @NonNull CharSequence message, int duration, Drawable icon, boolean withIcon) {
        custom(context, message, icon, DEFAULT_TEXT_COLOR, duration, withIcon);
    }

    public static void warning(@NonNull Context context, @NonNull CharSequence message) {
        warning(context, message, Toast.LENGTH_SHORT, true);
    }

    public static void warning(@NonNull Context context, @NonNull CharSequence message, int duration) {
        warning(context, message, duration, true);
    }

    public static void warning(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        custom(context, message, ToastyUtils.getDrawable(context, R.mipmap.toasty_warning_white_48dp),
                DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, withIcon, true);
    }

    public static void info(@NonNull Context context, @NonNull CharSequence message) {
        info(context, message, Toast.LENGTH_SHORT, true);
    }

    public static void info(@NonNull Context context, @NonNull CharSequence message, int duration) {
        info(context, message, duration, true);
    }

    public static void info(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        custom(context, message, ToastyUtils.getDrawable(context, R.mipmap.toasty_info_white_48dp), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, withIcon, true);
    }

    public static void success(@NonNull Context context, @NonNull CharSequence message) {
        success(context, message, Toast.LENGTH_SHORT, true);
    }

    public static void success(@NonNull Context context, @NonNull CharSequence message, int duration) {
        success(context, message, duration, true);
    }

    public static void success(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        custom(context, message, ToastyUtils.getDrawable(context, R.mipmap.toasty_check_white_48dp), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, withIcon, true);
    }

    public static void error(@NonNull Context context, @NonNull CharSequence message) {
        error(context, message, Toast.LENGTH_SHORT, true);
    }

    public static void error(@NonNull Context context, @NonNull CharSequence message, int duration) {
        error(context, message, duration, true);
    }

    public static void error(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon) {
        custom(context, message, ToastyUtils.getDrawable(context, R.mipmap.toasty_clear_white_48dp), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, withIcon, true);
    }

    public static void custom(@NonNull Context context, @NonNull CharSequence message, Drawable icon, @ColorInt int textColor, int duration, boolean withIcon) {
        custom(context, message, icon, textColor, -1, duration, withIcon, false);
    }

    public static void custom(@NonNull Context context, @NonNull CharSequence message, @DrawableRes int iconRes, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        custom(context, message, ToastyUtils.getDrawable(context, iconRes), textColor, tintColor, duration, withIcon, shouldTint);
    }

    public static void custom(@NonNull Context context, @NonNull CharSequence message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        if (currentToast == null) currentToast = new Toast(context);

        final View toastLayout = View.inflate(context, R.layout.toast_layout, null);
        final ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.toast_icon);
        final TextView toastText = (TextView) toastLayout.findViewById(R.id.toast_text);

        Drawable drawableFrame;
        if (shouldTint) drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
        else drawableFrame = ToastyUtils.getDrawable(context, R.mipmap.toasty_frame);
        ToastyUtils.setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null) throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            ToastyUtils.setBackground(toastIcon, icon);
        } else toastIcon.setVisibility(View.GONE);

        toastText.setText(message);
        toastText.setTextColor(textColor);
        toastText.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        currentToast.show();
    }
}
