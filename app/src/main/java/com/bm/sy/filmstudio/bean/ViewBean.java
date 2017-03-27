package com.bm.sy.filmstudio.bean;

import java.io.Serializable;

/**
 * 创建人: 李俊男
 * 类名称：ViewBean
 * 类描述：
 * 创建时间：2017/2/9 13:53
 */
public class ViewBean implements Serializable {
    private static final String TAG = "【ViewBean】";

    private String type;
    private int  pass;

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
