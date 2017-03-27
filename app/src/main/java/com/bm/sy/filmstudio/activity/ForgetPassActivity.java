package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseActivity;
import com.bm.sy.filmstudio.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：ForgetPassActivity
 * 类描述：忘记密码页面
 * 创建时间：2017/2/6 9:47
 */
public class ForgetPassActivity extends BaseActivity {
    private static final String TAG = "【ForgetPassActivity】";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_code_number)
    EditText etCodeNumber;
    @BindView(R.id.et_new_pass)
    EditText etNewPass;
    @BindView(R.id.et_sure_pass)
    EditText etSurePass;
    @BindView(R.id.btn_rec_pass)
    Button btnRecPass;



    public static void start(Activity context) {
        Intent starter = new Intent(context, ForgetPassActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_forget_pass );
        ButterKnife.bind(this);

    }

    @Override
    protected void initLogic() {
        tvTitleName.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvTitleName.setText("重置登录密码");
    }

    @OnClick({R.id.iv_back, R.id.tv_get_code, R.id.btn_rec_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_code:
                ToastUtil.showShort("获取验证码");
                break;
            case R.id.btn_rec_pass:
                ToastUtil.showShort("重置");
                break;
        }
    }
}
