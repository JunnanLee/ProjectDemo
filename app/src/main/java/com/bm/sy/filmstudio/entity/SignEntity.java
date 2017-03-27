package com.bm.sy.filmstudio.entity;

/**
 * 创建人: 李俊男
 * 类名称：SignEntity
 * 类描述：
 * 创建时间：2017/1/24 11:20
 */
public class SignEntity {
    private static final String TAG = "【SignEntity】";

    private String shopName;//店铺名
    private String place;//地点

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
