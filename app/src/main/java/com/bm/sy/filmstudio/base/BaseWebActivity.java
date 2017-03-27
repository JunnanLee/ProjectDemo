package com.bm.sy.filmstudio.base;

import android.os.Bundle;

/**
 * @author ygrs
 */
public abstract class BaseWebActivity extends BaseActivity {
	
	// 初始化UI setContentView
	protected abstract void initContentView(Bundle savedInstanceState);
	// 初始化控件
	protected abstract void initView();
    // 逻辑处理
	protected abstract void initLogic();

}
