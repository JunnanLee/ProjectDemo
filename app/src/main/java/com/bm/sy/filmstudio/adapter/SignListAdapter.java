package com.bm.sy.filmstudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.entity.SignListEntity;

import java.util.List;

/**
 * 创建人: 李俊男
 * 类名称：SignListAdapter
 * 类描述：
 * 创建时间：2017/2/13 15:45
 */
public class SignListAdapter extends BaseAdapter {
    private static final String TAG = "【SignListAdapter】";
    private List<SignListEntity>mList;

    private Context context;

    public SignListAdapter(List<SignListEntity> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList == null ? 0:mList.size();
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
        ViewHolder  holder ;
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sign_list_layout,null);
            holder = new ViewHolder();
            holder.tv_address = (TextView)convertView.findViewById(R.id.tv_address);
            holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView)convertView.findViewById(R.id.tv_time);
            holder.app_line = convertView.findViewById(R.id.app_line);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
            if(0==position){
                holder.app_line.setVisibility(View.INVISIBLE);
            }else{
                holder.app_line.setVisibility(View.VISIBLE);
            }
            holder.tv_name.setText(mList.get(position).getName());
            holder.tv_time.setText(mList.get(position).getTime());
            holder.tv_address.setText(mList.get(position).getAddress());
        }
        return convertView;
    }
    class ViewHolder{
        private TextView tv_name;//名字
        private TextView tv_time;//时间
        private TextView tv_address;//地址
        private View app_line;//分割线
    }
}
