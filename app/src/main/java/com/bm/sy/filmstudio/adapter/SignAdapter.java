package com.bm.sy.filmstudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.entity.SignEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: 李俊男
 * 类名称：SignAdapter
 * 类描述：
 * 创建时间：2017/1/24 11:17
 */
public class SignAdapter extends BaseAdapter {
    private static final String TAG = "【SignAdapter】";
    private List<SignEntity> mList;//
    private Context context;

    public SignAdapter(List<SignEntity> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
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
        final ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sign_lv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SignEntity beanTmp = mList.get(position);
        viewHolder.tvShopName.setText(beanTmp.getShopName());
        viewHolder.tvPlace.setText(beanTmp.getPlace());

        if(selectItem == position){
            viewHolder.ivChooseLogo.setImageResource(R.drawable.checked_logo);
        }else{
            viewHolder.ivChooseLogo.setImageResource(R.drawable.un_check_logo);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_place)
        TextView tvPlace;
        @BindView(R.id.iv_choose_logo)
        ImageView ivChooseLogo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private int selectItem = -1;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

}
