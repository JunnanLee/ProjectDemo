package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseFragmentActivity;
import com.bm.sy.filmstudio.bean.ViewBean;
import com.bm.sy.filmstudio.fragment.ArrayListFragment;
import com.bm.sy.filmstudio.util.ToastUtil;
import com.bm.sy.filmstudio.view.ChildViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：NewLogActivity
 * 类描述：新建日志
 * 创建时间：2017/2/7 17:10
 */
public class NewLogActivity extends BaseFragmentActivity {
    private static final String TAG = "【NewLogActivity】";
//    public static int NUM_ITEMS = 0;
    public static int NEW_LOG_REQUEST= 1001;
    public static int NEW_LOG_REQUEST_PROJECT= 1002;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_text_normal)
    TextView tvTextNormal;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_add_text)
    LinearLayout llAddText;
    @BindView(R.id.ll_add_pic)
    LinearLayout llAddPic;
    @BindView(R.id.ll_del)
    LinearLayout llDel;
    @BindView(R.id.iv_left_btn)
    ImageView ivLeftBtn;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.iv_right_btn)
    ImageView ivRightBtn;
    @BindView(R.id.pager)
    ChildViewPager pager;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    PagerScrollAdapter mAdapter;
    @BindView(R.id.tv_project)
    TextView tvProject;
    private int index = 0;//当前滚动坐标

    public static int type = 1;//


    private List<ViewBean> mList = new ArrayList<>();


    public static void start(Activity context) {
        Intent starter = new Intent(context, NewLogActivity.class);
        context.startActivity(starter);
//        context.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_new_log);
        ButterKnife.bind(this);
    }

    @Override
    protected void initLogic() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitleName.setVisibility(View.VISIBLE);
        tvTextNormal.setVisibility(View.VISIBLE);
        tvSubmit.setVisibility(View.GONE);
        tvTitleName.setText("新建日志");
        tvTextNormal.setText("存草稿");
        tvNotice.setText("当前为第0条/共" + "0条记录");

        mAdapter = new PagerScrollAdapter(getSupportFragmentManager(), mList);

        pager.setAdapter(mAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("当前滑动页", position + "");
                index = position;
//                MessageUtil.sendMessage(mHandler, 1212, pics.get(position).getTime());
                tvNotice.setText("当前为第" + (position + 1) + "条/共" + mList.size() + "条记录");
                pager.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_text_normal, R.id.tv_time, R.id.ll_add_text, R.id.ll_add_pic, R.id.ll_del, R.id.iv_left_btn, R.id.iv_right_btn, R.id.tv_submit,R.id.tv_project})
    public void onClick(View view) {
        Intent intent;
        ViewBean bean;
        switch (view.getId()) {
            case R.id.iv_back://
                finish();
                break;
            case R.id.tv_text_normal:
                break;
            case R.id.tv_time:
                 intent  = new Intent(NewLogActivity.this,DateChooseActivity.class);
                startActivityForResult(intent,NEW_LOG_REQUEST);
                break;
            case R.id.ll_add_text://添加一个文本
                pager.addHeight(mList.size() - 1, pager.getHeight());

                bean = new ViewBean();
                bean.setType("1");
                mList.add(bean);
                mAdapter.notifyDataSetChanged();
                tvNotice.setText("当前为第" + (index + 1) + "条/共" + mList.size() + "条记录");
//                ToastUtil.showShort("");
                Toast.makeText(this, "您新增了一条表单类记录", Toast.LENGTH_SHORT).show();
                tvSubmit.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_add_pic://添加一个图文
//                type =2;
                bean = new ViewBean();
                bean.setType("2");
                mList.add(bean);
                mAdapter.notifyDataSetChanged();
                tvNotice.setText("当前为第" + (index + 1) + "条/共" + mList.size() + "条记录");
                Toast.makeText(this, "您新增了一条表图文类记录", Toast.LENGTH_SHORT).show();
                tvSubmit.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_del://删除
                if (mList.size() > 1) {
                    mList.remove(index);
                    mAdapter.notifyDataSetChanged();
                    tvNotice.setText("当前为第" + (index + 1) + "条/共" + mList.size() + "条记录");
                } else {
                    if (mList.size() == 1) {
                        mList.remove(index);
                        mAdapter.notifyDataSetChanged();
                        tvNotice.setText("当前为第0条/共" + mList.size() + "条记录");
                    }
                }
                if (0 == mList.size()) {
                    tvSubmit.setVisibility(View.GONE);
                } else {
                    Toast.makeText(this, "您删除了一条记录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_left_btn://下一张
                if (index > 0) {
                    pager.setCurrentItem(index - 1);
                }
                break;
            case R.id.iv_right_btn://上一张
                if (mList.size() > index) {
                    pager.setCurrentItem(index + 1);
                }
                break;
            case R.id.tv_submit://提交
                break;
            case R.id.tv_project:
                intent  = new Intent(NewLogActivity.this,ProjectChangeActivity.class);
                startActivityForResult(intent,NEW_LOG_REQUEST_PROJECT);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (NEW_LOG_REQUEST==requestCode && RESULT_OK ==  resultCode){
            String time = data.getStringExtra("time");
            tvTime.setText(time);
        }

        if(NEW_LOG_REQUEST_PROJECT == requestCode && RESULT_OK ==  resultCode){
            String project =  data.getStringExtra("project");
            tvProject.setText(project);
        }
    }

    public class PagerScrollAdapter extends FragmentStatePagerAdapter {
        private static final String TAG = "【PagerScrollAdapter】";
        private List<ViewBean> mList;

        public PagerScrollAdapter(FragmentManager fm, List<ViewBean> mList) {
            super(fm);
            this.mList = mList;
            //TODO

        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position, mList);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


    }


}
