package com.bm.sy.filmstudio.entity;

/**
 * 创建人: 李俊男
 * 类名称：ProjectEntity
 * 类描述：
 * 创建时间：2017/2/13 17:52
 */
public class ProjectEntity {
    private static final String TAG = "【ProjectEntity】";

    private int pic;
    private String filmName;//项目名称
    private String time;
    private String actor;//导演
    private String star;//主演
    private String ticket;//票房

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
