package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseFragmentActivity;
import com.bm.sy.filmstudio.fragment.JournalFragment;
import com.bm.sy.filmstudio.fragment.SignFragment;
import com.bm.sy.filmstudio.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: 李俊男
 * 类名称：SignJournalActivity
 * 类描述：签到  日志页面
 * 创建时间：2017/1/22 16:34
 */
public class SignJournalActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "【SignJournalActivity】";
    @BindView(R.id.tab_fragment)
    FrameLayout tabFragment;
    @BindView(R.id.btn_tab_sign)
    RadioButton btnTabSign;//签到
    @BindView(R.id.btn_tab_log)
    RadioButton btnTabLog;//日志
    @BindView(R.id.rg_tab_switch)
    RadioGroup rgTabSwitch;
    private Fragment currentFragment = new Fragment();
    private SignFragment signFragment;//签到 fragment
    private JournalFragment journalFragment;//日志 fragment
    private FragmentManager fragmentManager;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_sign_journal);
        ButterKnife.bind(this);
    }

    public static void start(Activity context) {
        Intent starter = new Intent(context, SignJournalActivity.class);
        context.startActivity(starter);
//        context.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);

    }

    @Override
    protected void initLogic() {
        rgTabSwitch.setOnCheckedChangeListener(this);
        signFragment = new SignFragment();
        journalFragment = new JournalFragment();
        fragmentManager = this.getSupportFragmentManager();
        showFragment(signFragment);
    }

    /**
     * 展示Fragment
     */
    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.tab_fragment, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.scale_translate,
//                R.anim.my_alpha_action);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn_tab_sign://签到
                btnTabSign.setTextColor(getResources().getColor(R.color.login_btn_color));
                btnTabLog.setTextColor(getResources().getColor(R.color.main_text_color_gray));
                showFragment(signFragment);
                ToastUtil.showShort("1");
                break;
            case R.id.btn_tab_log://日志
                btnTabSign.setTextColor(getResources().getColor(R.color.main_text_color_gray));
                btnTabLog.setTextColor(getResources().getColor(R.color.login_btn_color));
                showFragment(journalFragment);
                ToastUtil.showShort("2");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
