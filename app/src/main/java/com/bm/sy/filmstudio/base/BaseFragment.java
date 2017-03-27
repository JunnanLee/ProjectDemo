package com.bm.sy.filmstudio.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.sy.filmstudio.util.LogUtil;


/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    private static final String LOG_TAG = "BaseFragment";
    
    protected View rootView;
    
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = initContentView(inflater, container, savedInstanceState);
        initLogic();
        return view;
    }
    
    protected View createFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, int resId) {
        View view = null;
        if (null != inflater) {
            view = inflater.inflate(resId, container, false);
        }
        
        // TODO
        if (null == view) {
            Log.e(LOG_TAG, "fragment view is not created！");
        }
        
        return view;
    }

	// 初始化UI setContentView
	protected abstract View initContentView(LayoutInflater inflater, @Nullable ViewGroup container,
                                            @Nullable Bundle savedInstanceState);
    // 逻辑处理
	protected abstract void initLogic();

    /**
     * 获取fragment的资源
     */
    protected Resources getFragmentResource() {
        Resources resources = null;
        if (isAdded()) {
            resources = getResources();
            if (null == resources) {
                LogUtil.e(LOG_TAG, "getResources is error");
            }
        } else {
            LogUtil.e(LOG_TAG, "fragment is not attached to acitivity");
        }

        return resources;
    }

    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }
}
