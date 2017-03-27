package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseFragmentActivity;
import com.bm.sy.filmstudio.bean.ViewBean;
import com.bm.sy.filmstudio.fragment.ExamineFragment;
import com.bm.sy.filmstudio.fragment.PassFragment;
import com.bm.sy.filmstudio.view.ChildViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：JournalExamineActivity
 * 类描述：
 * 创建时间：2017/2/14 13:07
 */
public class JournalExamineActivity extends BaseFragmentActivity {
    private static final String TAG = "【JournalExamineActivity】";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_project)
    TextView tvProject;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_left_btn)
    ImageView ivLeftBtn;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.iv_right_btn)
    ImageView ivRightBtn;
    @BindView(R.id.pager)
    ChildViewPager pager;
    private List<ViewBean> mList = new ArrayList<>();

    private PagerScrollAdapter mAdapter;
    private int index = 0;//当前滚动坐标

    public static void start(Activity context) {
        Intent starter = new Intent(context, JournalExamineActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_journal_examine);
        ButterKnife.bind(this);

        ivBack.setVisibility(View.VISIBLE);
        tvTitleName.setVisibility(View.VISIBLE);
        tvTitleName.setText("周大鹏日志");
    }

    @Override
    protected void initLogic() {

        //假数据
        for(int i=0;i<3;i++){
            ViewBean bean = new ViewBean();
            mList.add(bean);
        }
        tvProject.setText("盗墓笔记");
        tvTime.setText("2017年2月14日");

        tvNotice.setText("当前为第" + (1) + "条/共" + mList.size() + "条记录");
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

    @OnClick({R.id.iv_back, R.id.tv_project, R.id.tv_time, R.id.iv_left_btn, R.id.iv_right_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_project:

                break;
            case R.id.tv_time:

                break;
            case R.id.iv_left_btn:
                if (index > 0) {
                    pager.setCurrentItem(index - 1);
                }
                break;
            case R.id.iv_right_btn:
                if (mList.size() > index) {
                    pager.setCurrentItem(index + 1);
                }
                break;
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
            return ExamineFragment.newInstance(position, mList);
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
