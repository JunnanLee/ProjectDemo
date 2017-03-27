package com.bm.sy.filmstudio.entity;

/**
 * 创建人: 李俊男
 * 类名称：SignListEntity
 * 类描述：
 * 创建时间：2017/2/13 15:38
 */
public class SignListEntity {
    private static final String TAG = "【SignListEntity】";

    private String name;//签到标题
    private String address;//地址
    private String time;//时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
