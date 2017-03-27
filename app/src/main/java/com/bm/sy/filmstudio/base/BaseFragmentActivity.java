package com.bm.sy.filmstudio.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bm.sy.filmstudio.util.ActivityCollector;


/**
 * FragmentActivity基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		BaseApp.pushActivity(this);
		initContentView(bundle);
		ActivityCollector.addActivity(this);
		initLogic();
	}

	// 初始化UI setContentView
	protected abstract void initContentView(Bundle savedInstanceState);
    // 逻辑处理
	protected abstract void initLogic();

	protected void onResume() {
		super.onResume();
	}
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollector.removeActivity(this);
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
