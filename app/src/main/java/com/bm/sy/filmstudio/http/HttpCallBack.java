package com.bm.sy.filmstudio.http;

import android.content.Context;
import android.util.Log;

import com.bm.sy.filmstudio.util.ToastUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by: zanjr
 * date: 2016/7/28
 * time: 16:34
 * description:
 */
public abstract class HttpCallBack<T> extends Callback<T> {

    private Class clz;
    private boolean showDialog;
    private Context context;

    public HttpCallBack(Class clz, boolean showDialog, Context context) {
        this.clz = clz;
        this.showDialog = showDialog;
        this.context = context;
    }

    public HttpCallBack(Class clz) {
        this.clz = clz;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String json = response.body().string();
//        StorageUtil.saveText2File(TimeC.getLogTime() + json, StorageUtil.getFileAbsoluteSavePath(), "logger.txt", true);
        if (OkHttpUtils.getInstance().getOkHttpClient().interceptors().size() != 0) {
            Log.e("HttpCallBack", json);
        }
        return (T) new Gson().fromJson(json, clz);
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (showDialog) {
            HUD.getInstance().show(context);
        }
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        if (showDialog) {
            HUD.getInstance().dismiss();
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        if (OkHttpUtils.getInstance().getOkHttpClient().interceptors().size() != 0) {
            e.printStackTrace();
        }
        HUD.getInstance().dismiss();
        ToastUtil.showShort("网络貌似不给力呀！");
    }
}
