package com.bm.sy.filmstudio.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bm.sy.filmstudio.util.ActivityCollector;


/**
 * @author zhaojy
 * @ClassName BaseActivity
 * @date 2016-4-15
 * @Description: TODO
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        BaseApp.pushActivity(this);
        initContentView(savedInstanceState);
        initLogic();
        ActivityCollector.addActivity(this);
        //Collections
    }

    // 初始化UI setContentView
    protected abstract void initContentView(Bundle savedInstanceState);

    // 逻辑处理
    protected abstract void initLogic();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void onBack(View v){
        finish();
    }

    protected void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("SplashScreen");
    }
    protected void onPause() {
        super.onPause();
    }
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
