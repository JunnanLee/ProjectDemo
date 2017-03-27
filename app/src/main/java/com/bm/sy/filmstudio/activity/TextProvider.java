package com.bm.sy.filmstudio.activity;

/**
 * 创建人: 李俊男
 * 类名称：TextProvider
 * 类描述：
 * 创建时间：2017/2/9 15:07
 */

public interface TextProvider {
    public String getTextForPosition(int position);
    public int getCount();
}

