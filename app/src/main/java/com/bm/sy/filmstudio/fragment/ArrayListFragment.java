package com.bm.sy.filmstudio.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.activity.JournalSearchActivity;
import com.bm.sy.filmstudio.activity.NewLogActivity;
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
 * 类名称：ArrayListFragment
 * 类描述：
 * 创建时间：2017/2/8 12:00
 */
@AFI(contentViewId = R.layout.fragment_pager_list)
public class ArrayListFragment extends Fragment {
    int mNum,type;
    @BindView(R.id.et_write_type)
    TextView etWriteType;
    @BindView(R.id.et_unit)
    TextView etUnit;
    @BindView(R.id.et_people)
    TextView etPeople;
    @BindView(R.id.tv_ratio)
    TextView tvRatio;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.et_notice)
    EditText etNotice;
    @BindView(R.id.gv_pic)
    BMGridViewForScrollView gvPic;

    AddPicAdapter add_adapter;
    List<ViewBean> mList;
    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static ArrayListFragment newInstance(int num,List<ViewBean> mList) {
        ArrayListFragment f = new ArrayListFragment();

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
        int layout = 0;
        switch (mList.get(mNum).getType()){
            case "1":
                 layout = R.layout.fragment_pager_list;
                break;
            case "2":
                 layout = R.layout.fragment_pager_list_s;
                break;
            default:
                break;
        }
        View v = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, v);
        /**
         * 处理edit 在 scroll 中 滚动冲突的问题
         */
        etNotice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        add_adapter = new AddPicAdapter(v.getContext());

        PicBean entity = new PicBean();
        entity.setPicPath(R.drawable.take_photo);
        entity.setEntityType(BaseEntity.BaseEntityType.COMMON_CONTENT_ADD_IMG);
        add_adapter.getmList().add(entity);

        gvPic.setAdapter(add_adapter);

        etWriteType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JournalSearchActivity.start((Activity) v.getContext());
            }
        });


        return v;

    }
    /**
     * 适配器
     */
    private class AddPicAdapter extends BaseAdapter {

        private List<PicBean> mList;

        private Context context;

        public AddPicAdapter(Context context) {
            this.context = context;
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
        public int getItemViewType(int position) {
            return mList.get(position).getEntityType().ordinal();
        }

        @Override
        public int getViewTypeCount() {
            return BaseEntity.BaseEntityType.values().length;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ContentImgHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_add_other_pic, parent,
                        false);

                holder = new ContentImgHolder();
                holder.iv_deleteContentImg = (ImageView) convertView.findViewById(R.id.delete_logo);//删除图片图标
                holder.iv_contentImg = (ImageView) convertView.findViewById(R.id.item_iv_pics);//内容图片

                convertView.setTag(holder);
            }
            holder = (ContentImgHolder) convertView.getTag();
            final PicBean contentImgEntity = mList.get(position);
            if (BaseEntity.BaseEntityType.COMMON_CONTENT_ADD_IMG == contentImgEntity.getEntityType()) {
                //添加图片，需要隐藏删除图标
                holder.iv_deleteContentImg.setVisibility(View.GONE);
            } else {
                //内容图片，需要显示删除图标，并且给该图标设置监听事件

//                TakePhotoUtils.pathToBit(holder.iv_contentImg, contentImgEntity.getPic());

                Glide.with(context)
                        .load(new File(contentImgEntity.getFilePath()))
                        .centerCrop()
                        .into(holder.iv_contentImg);

                holder.iv_deleteContentImg.setVisibility(View.VISIBLE);
                holder.iv_deleteContentImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                //添加图片
                holder.iv_contentImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
            return convertView;
        }

        public List<PicBean> getmList() {
            if (mList == null) {
                mList = new ArrayList<>();
            }
            return mList;
        }

        public void setmList(List<PicBean> mList) {
            this.mList = mList;
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
