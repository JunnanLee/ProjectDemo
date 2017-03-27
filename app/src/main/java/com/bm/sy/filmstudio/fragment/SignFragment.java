package com.bm.sy.filmstudio.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.activity.SignHistoryDateActivity;
import com.bm.sy.filmstudio.activity.SignSuccessActivity;
import com.bm.sy.filmstudio.adapter.SignAdapter;
import com.bm.sy.filmstudio.base.AFI;
import com.bm.sy.filmstudio.base.BaseFragment;
import com.bm.sy.filmstudio.entity.SignEntity;
import com.bm.sy.filmstudio.util.LogUtil;
import com.bm.sy.filmstudio.view.BMRadioButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：SignFragment
 * 类描述：签到 页面 切换页面
 * 创建时间：2017/1/23 13:29
 */


@AFI(contentViewId = R.layout.fg_sing_ll)
public class SignFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener,AdapterView.OnItemClickListener {
    private static final String TAG = "【SignFragment】";


    @BindView(R.id.tv_text_left)
    TextView tvTextLeft;//时间
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;//标题
    @BindView(R.id.iv_title_logo)
    ImageView ivTitleLogo;//日期按钮
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;//头像
    @BindView(R.id.tv_name)
    TextView tvName;//姓名
    @BindView(R.id.rl_start)
    RelativeLayout rlStart;//work 开始
    @BindView(R.id.rl_end)
    RelativeLayout rlEnd;//work   结束
    @BindView(R.id.menu_tab_film)
    BMRadioButton menuTabFilm;//片方
    @BindView(R.id.menu_tab_park)
    BMRadioButton menuTabPark;//影院
    @BindView(R.id.btn_tab_manage)
    BMRadioButton btnTabManage;//影管
    @BindView(R.id.btn_tab_line)
    BMRadioButton btnTabLine;//院线
    @BindView(R.id.btn_tab_pull)
    BMRadioButton btnTabPull;//营销推广
    @BindView(R.id.rg_tab_menu)
    RadioGroup rgTabMenu;//rg
    @BindView(R.id.lv_sign)
    ListView lvSign;//列表
    @BindView(R.id.tv_sing)
    TextView tvSing;//立即签到

    private SignAdapter mAdapter;//适配器
    private List<SignEntity> mList = new ArrayList<>();



    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = createFragmentView(inflater, container, R.layout.fg_sing_ll);
        if (null == rootView) {
            LogUtil.d(TAG, "fragment view is not created！");
        }
        ButterKnife.bind(this, rootView);
        return this.rootView;
    }

    @Override
    protected void initLogic() {
        tvTextLeft.setVisibility(View.VISIBLE);
        tvTitleName.setVisibility(View.VISIBLE);
        ivTitleLogo.setVisibility(View.VISIBLE);
        tvTitleName.setText("签到");
        getTimeUrl();
        initData();
        rgTabMenu.setOnCheckedChangeListener(this);
        mAdapter = new SignAdapter(mList,rootView.getContext());
        lvSign.setAdapter(mAdapter);

        lvSign.setOnItemClickListener(this);
        //添加假数据
    }

    /**
     * 添加假数据
     */
    private void initData() {
        for(int i=0;i<10;i++){
            SignEntity bean  = new SignEntity();
            bean.setShopName("SC上影影城新衡山店");
            bean.setPlace("上海市徐汇区横上路220号");
            mList.add(bean);
        }
    }

    /**
     * 获取系统时间实时
     */
    private void getTimeUrl() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/ HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTextLeft.setText(str);
    }

    @OnClick({R.id.iv_title_logo, R.id.rl_start, R.id.rl_end, R.id.tv_sing})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_logo:
                SignHistoryDateActivity.start((Activity) rootView.getContext());
                break;
            case R.id.rl_start:
                break;
            case R.id.rl_end:
                break;
            case R.id.tv_sing:
                SignSuccessActivity.start((Activity) rootView.getContext());
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.menu_tab_film:
                menuTabFilm.setTextColor(getResources().getColor(R.color.white));
                menuTabPark.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabManage.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabLine.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPull.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                break;
            case R.id.menu_tab_park:
                menuTabFilm.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabPark.setTextColor(getResources().getColor(R.color.white));
                btnTabManage.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabLine.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPull.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                break;
            case R.id.btn_tab_manage:
                menuTabFilm.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabPark.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabManage.setTextColor(getResources().getColor(R.color.white));
                btnTabLine.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPull.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                break;
            case R.id.btn_tab_line:
                menuTabFilm.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabPark.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabManage.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabLine.setTextColor(getResources().getColor(R.color.white));
                btnTabPull.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                break;
            case R.id.btn_tab_pull:
                menuTabFilm.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabPark.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabManage.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabLine.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPull.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mAdapter.setSelectItem(position);
            mAdapter.notifyDataSetChanged();
    }
}
