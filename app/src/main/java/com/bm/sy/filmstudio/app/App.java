package com.bm.sy.filmstudio.app;

import com.bm.sy.filmstudio.base.BaseApp;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by lijn on 2017/1/17.
 */

public class App extends BaseApp {

    @Override
    protected void onInitCreate() {

        initOkHttp();//初始化http 网路设置
    }

    /**
     * 初始化设置OKHttp
     */
    private void initOkHttp() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }
}
