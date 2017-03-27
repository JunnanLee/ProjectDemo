package com.bm.sy.filmstudio.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 创建人: 李俊男
 * 类名称：BMGridViewForScrollView
 * 类描述：
 * 创建时间：2017/2/8 16:50
 */
public class BMGridViewForScrollView extends GridView {

    public BMGridViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public BMGridViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
