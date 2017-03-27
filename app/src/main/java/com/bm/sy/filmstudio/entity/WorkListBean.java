package com.bm.sy.filmstudio.entity;

/**
 * 创建人: 李俊男
 * 类名称：WorkListBean
 * 类描述：
 * 创建时间：2017/2/7 11:21
 */
public class WorkListBean {
    private static final String TAG = "【WorkListBean】";

    private String filmName;//电影名
    private String time;//提交时间
    private String notice;//记录

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
