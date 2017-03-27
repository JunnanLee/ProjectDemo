package com.bm.sy.filmstudio.text;

import android.graphics.Color;

import com.bm.sy.filmstudio.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 创建人: 李俊男
 * 类名称：RecyclerTextAdapter
 * 类描述：
 * 创建时间：2017/3/8 14:26
 */
public class RecyclerTextAdapter extends BaseQuickAdapter<TextEntity,BaseViewHolder> {
    private static final String TAG = "【RecyclerTextAdapter】";

    public RecyclerTextAdapter(int layoutResId, List<TextEntity> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, TextEntity item) {
        helper.setText(R.id.text, item.getTitle());
        switch (item.getType()){
            case 1:
                helper.setTextColor(R.id.text, Color.WHITE);
                helper.setBackgroundColor(R.id.cv_bg,Color.BLUE);
                break;
            case 2:
                helper.setTextColor(R.id.text, Color.BLUE);
                helper.setBackgroundColor(R.id.cv_bg,Color.WHITE);
                break;
        }
        helper.setImageResource(R.id.icon, item.getPic());
    }
}
