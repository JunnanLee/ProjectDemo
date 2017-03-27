package com.bm.sy.filmstudio.bean;

import com.bm.sy.filmstudio.entity.BaseEntity;

/**
 * 创建人: 李俊男
 * 类名称：PicBean
 * 类描述：
 * 创建时间：2017/2/9 9:47
 */
public class PicBean extends BaseEntity{
    private static final String TAG = "【PicBean】";

    private int picPath;

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPicPath() {
        return picPath;
    }

    public void setPicPath(int picPath) {
        this.picPath = picPath;
    }
}
