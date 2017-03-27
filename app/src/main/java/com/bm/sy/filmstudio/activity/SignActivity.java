package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：SignActivity
 * 类描述：注册页面
 * 创建时间：2017/2/6 11:01
 */
public class SignActivity extends BaseActivity {
    private static final String TAG = "【SignActivity】";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.et_sure_pwd)
    EditText etSurePwd;
    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_id_card)
    EditText etIdCard;
    @BindView(R.id.iv_id_a)
    ImageView ivIdA;
    @BindView(R.id.iv_id_b)
    ImageView ivIdB;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_code_number)
    EditText etCodeNumber;
    @BindView(R.id.tv_sign_now)
    TextView tvSignNow;


    public static void start(Activity context) {
        Intent starter = new Intent(context, SignActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_sign);
        ButterKnife.bind(this);
    }
    @Override
    protected void initLogic() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitleName.setVisibility(View.VISIBLE);
        tvTitleName.setText("注册");
    }
    @OnClick({R.id.iv_back, R.id.iv_pic, R.id.iv_id_a, R.id.iv_id_b, R.id.tv_get_code, R.id.tv_sign_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_pic:
                break;
            case R.id.iv_id_a:
                break;
            case R.id.iv_id_b:
                break;
            case R.id.tv_get_code:
                break;
            case R.id.tv_sign_now:
                break;
        }
    }
}
