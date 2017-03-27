package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：SignSuccessActivity
 * 类描述：签到成功页面
 * 创建时间：2017/1/24 14:50
 */
public class SignSuccessActivity extends BaseActivity {
    private static final String TAG = "【SignSuccessActivity】";
    @BindView(R.id.rl_sign_finish)
    LinearLayout rlSignFinish;


    public static void start(Activity context) {
        Intent starter = new Intent(context, SignSuccessActivity.class);
        context.startActivity(starter);
        context.overridePendingTransition(R.anim.zoom_enter, 0);
    }


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_sign_success);
        ButterKnife.bind(this);
    }

    @Override
    protected void initLogic() {
//        backgroundAlpha((float) 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @OnClick(R.id.rl_sign_finish)
    public void onClick() {
        finish();
        overridePendingTransition(0, 0);
    }
}
