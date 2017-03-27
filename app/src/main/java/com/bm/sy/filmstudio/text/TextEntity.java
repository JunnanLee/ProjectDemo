package com.bm.sy.filmstudio.text;

/**
 * 创建人: 李俊男
 * 类名称：TextEntity
 * 类描述：
 * 创建时间：2017/3/8 14:29
 */
public class TextEntity {
    private static final String TAG = "【TextEntity】";

    private String title;
    private int pic;
    private int type;//



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
