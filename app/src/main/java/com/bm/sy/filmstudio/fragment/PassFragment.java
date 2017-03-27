package com.bm.sy.filmstudio.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.activity.JournalSearchActivity;
import com.bm.sy.filmstudio.base.AFI;
import com.bm.sy.filmstudio.bean.PicBean;
import com.bm.sy.filmstudio.bean.ViewBean;
import com.bm.sy.filmstudio.entity.BaseEntity;
import com.bm.sy.filmstudio.view.BMGridViewForScrollView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: 李俊男
 * 类名称：PassFragment
 * 类描述：
 * 创建时间：2017/2/14 13:42
 */
@AFI(contentViewId = R.layout.fragment_pass_journal)
public class PassFragment extends Fragment {
    private static final String TAG = "【PassFragment】";
    int mNum, type;
    AddPicAdapter add_adapter;
    List<ViewBean> mList =new ArrayList<>();;
    List<PicBean> picList =new ArrayList<>();

    @BindView(R.id.et_opinion)
    TextView etOpinion;
    @BindView(R.id.btn_pass)
    TextView btnPass;
    @BindView(R.id.et_write_type)
    TextView etWriteType;
    @BindView(R.id.et_unit)
    TextView etUnit;
    @BindView(R.id.et_people)
    TextView etPeople;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_ratio)
    TextView tvRatio;
    @BindView(R.id.tv_real_per)
    TextView tvRealPer;
    @BindView(R.id.et_notice)
    TextView etNotice;
    @BindView(R.id.gv_pic)
    BMGridViewForScrollView gvPic;

    public static PassFragment newInstance(int num, List<ViewBean> mList) {
        PassFragment f = new PassFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
//        args.putInt("type",type);
        args.putSerializable("type", (Serializable) mList);
        f.setArguments(args);

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        mList = (List<ViewBean>) (getArguments() != null ? getArguments().getSerializable("type") : 1);

    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pass_journal, container, false);
        ButterKnife.bind(this, v);


        if(0==mList.get(mNum).getPass()){//未通过
            btnPass.setText("未通过");
            btnPass.setBackgroundResource(R.drawable.shape_un_pass_bg_color);
            btnPass.setTextColor(getResources().getColor(R.color.main_text_color_gray));
        }else{
            btnPass.setText("通过");
            btnPass.setBackgroundResource(R.drawable.shape_pass_bg_color);
            btnPass.setTextColor(getResources().getColor(R.color.login_btn_color));
        }

        add_adapter = new AddPicAdapter(v.getContext(),picList);
        gvPic.setAdapter(add_adapter);

        etWriteType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JournalSearchActivity.start((Activity) v.getContext());
            }
        });
        setDataBean();//设置假数据
        return v;

    }

    private void setDataBean() {
        etOpinion.setText("做的不错");
        etWriteType.setText("走访");
        etUnit.setText("SFC上影影城永茂店");
        etPeople.setText("穆加薪/总经理/192545742254");
        tvDate.setText("2017年2月14日");
        tvRatio.setText("50%");
        tvRealPer.setText("30%");
        etNotice.setText("沟通良好，表示愿意支持本片");

        for(int i=0;i<3;i++){
            PicBean pic = new PicBean();
            pic.setPicPath(R.drawable.demo_project);
            picList.add(pic);
        }
        add_adapter.notifyDataSetChanged();
    }

    /**
     * 适配器
     */
    private class AddPicAdapter extends BaseAdapter {

        private List<PicBean> mList;

        private Context context;

        public AddPicAdapter(Context context, List<PicBean> mList) {
            this.context = context;
            this.mList = mList;
        }
        @Override
        public int getCount() {
            if (mList == null) {
                return 0;
            } else if (mList.size() > 4) {
                return 4;
            } else {
                return mList.size();
            }
        }
        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ContentImgHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_add_other_pic, parent,
                        false);
                holder = new ContentImgHolder();
                holder.iv_deleteContentImg = (ImageView) convertView.findViewById(R.id
                        .delete_logo);//删除图片图标
                holder.iv_contentImg = (ImageView) convertView.findViewById(R.id.item_iv_pics);//内容图片

                convertView.setTag(holder);
            } else {
                holder = (ContentImgHolder) convertView.getTag();
            }

            final PicBean contentImgEntity = mList.get(position);

//            Glide.with(context)
//                    .load(new File(contentImgEntity.getFilePath()))
//                    .centerCrop()
//                    .into(holder.iv_contentImg);

            holder.iv_contentImg.setImageResource(contentImgEntity.getPicPath());
            holder.iv_deleteContentImg.setVisibility(View.GONE);
            return convertView;
        }
        private class ContentImgHolder {
            /**
             * 内容图片
             */
            ImageView iv_contentImg;
            /**
             * 删除内容图片
             */
            ImageView iv_deleteContentImg;
        }
    }

}
