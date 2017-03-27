package com.bm.sy.filmstudio.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseActivity;
import com.bm.sy.filmstudio.text.RecyclerListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 登录页面  李俊男
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.rl_login_layout)
    RelativeLayout rlLoginLayout;//变高布局
    @BindView(R.id.et_login_num)
    EditText etLoginNum;//账号
    @BindView(R.id.num_v)
    View numV;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;//密码
    @BindView(R.id.pwd_v)
    View pwdV;
    @BindView(R.id.cb_login_remember)
    CheckBox cbLoginRemember;//记住密码
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;//忘记密码？
    @BindView(R.id.login_btn)
    Button loginBtn;//登录
    @BindView(R.id.tv_sign)
    TextView tvSign;//注册

    @Override
    protected void initContentView(Bundle savedInstanceState) {

        setContentView(R.layout.ac_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initLogic() {

        calculationWith();
        changeViewColor();//tt
    }

    /**
     * 计算布局宽度
     */
    private void calculationWith() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int rl_with = (int)((dm.widthPixels )* 0.6);

        LinearLayout.LayoutParams rlParams = (LinearLayout.LayoutParams) rlLoginLayout.getLayoutParams();
        rlParams.height = rl_with;
        rlLoginLayout.setLayoutParams(rlParams);
    }

    /**
     * 改变控件颜色
     */
    private void changeViewColor() {
        //帐号
        etLoginNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    numV.setBackgroundColor(getResources().getColor(R.color.login_btn_color));
                }else{
                    numV.setBackgroundColor(getResources().getColor(R.color.app_line));
                }
            }
        });
        //密码
        etLoginPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    pwdV.setBackgroundColor(getResources().getColor(R.color.login_btn_color));
                }else{
                    pwdV.setBackgroundColor(getResources().getColor(R.color.app_line));
                }
            }
        });
        cbLoginRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
    }

    @OnClick({R.id.forget_pwd, R.id.login_btn, R.id.tv_sign,R.id.rl_login_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_pwd:
                ForgetPassActivity.start(LoginActivity.this);
                break;
            case R.id.login_btn:
                SignJournalActivity.start(LoginActivity.this);
                break;
            case R.id.tv_sign:
                SignActivity.start(LoginActivity.this);
                break;
            case R.id.rl_login_layout:
                RecyclerListActivity.start(LoginActivity.this);
                break;
        }
    }
}


