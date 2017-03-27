package com.bm.sy.filmstudio.base;

/**
 * Created by lijn on 2017/1/17.
 */


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import java.util.Stack;

public abstract class BaseApp extends Application {
    protected static Context applicationContext;
    protected static BaseApp instance;
    private static Stack<Activity> activityStack = new Stack();

    public BaseApp() {
    }

    public void onCreate() {
        applicationContext = this.getApplicationContext();
        instance = this;
        this.onInitCreate();
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    public static BaseApp getInstance() {
        return instance;
    }

    public static Resources getAppResources() {
        return getAppResources();
    }

    public static void popActivity(Activity activity) {
        if(activity != null) {
            activity.finish();
            if(activityStack != null) {
                activityStack.remove(activity);
            }

            activity = null;
        }

    }

    public static void pushActivity(Activity activity) {
        if(activityStack != null) {
            activityStack.add(activity);
        }

    }

    public static void clearAllActivity() {
        while(activityStack != null && !activityStack.isEmpty()) {
            Activity activity = (Activity)activityStack.pop();
            if(activity != null) {
                activity.finish();
            }
        }

    }

    protected abstract void onInitCreate();
}
