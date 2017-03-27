package com.bm.sy.filmstudio.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * 创建人：李俊男
 * 类名称：ToastUtil
 * 类描述：
 * 创建时间：2017/1/20 14:12
 */
public class ToastUtil {

    private static final String TAG = "【ToastUtil】";
    private static Context appContext;
    public static boolean isShow = true;

    public ToastUtil() {
    }

    public static void setContext(Context context) {
        appContext = context;
    }

    public static void showShort(Context context, CharSequence message) {
        if(appContext == null) {
            appContext = context;
        }

        if(isShow) {
            Toast.makeText(context, message, 0).show();
        }

    }

    public static void showShort(Context context, int message) {
        if(appContext == null) {
            appContext = context;
        }

        if(isShow) {
            Toast.makeText(context, message, 0).show();
        }

    }

    public static void showLong(Context context, CharSequence message) {
        if(appContext == null) {
            appContext = context;
        }

        if(isShow) {
            Toast.makeText(context, message, 1).show();
        }

    }

    @SuppressLint("ShowToast")
    public static void showLong(Context context, int message) {
        if(appContext == null) {
            appContext = context;
        }

        if(isShow) {
            Toast.makeText(context, message, 1).show();
        }

    }

    public static void show(Context context, CharSequence message, int duration) {
        if(appContext == null) {
            appContext = context;
        }

        if(isShow) {
            Toast.makeText(context, message, duration).show();
        }

    }

    public static void show(Context context, int message, int duration) {
        if(appContext == null) {
            appContext = context;
        }

        if(isShow) {
            Toast.makeText(context, message, duration).show();
        }

    }

    public static void showShort(CharSequence message) {
        if(isShow && appContext != null) {
            showShort(appContext, message);
        }

    }

    public static void showShort(int message) {
        if(isShow && appContext != null) {
            showShort(appContext, message);
        }

    }

    public static void showLong(CharSequence message) {
        if(isShow && appContext != null) {
            showLong(appContext, message);
        }

    }

    public static void showLong(int message) {
        if(isShow && appContext != null) {
            showLong(appContext, message);
        }

    }

    public static void show(CharSequence message, int duration) {
        if(isShow && appContext != null) {
            show(appContext, message, duration);
        }

    }

    public static void show(int message, int duration) {
        if(isShow && appContext != null) {
            show(appContext, message, duration);
        }

    }
}

