package com.bm.sy.filmstudio.entity;

/**
 * 创建人：李俊男
 * 类名称：BaseEntity
 * 类描述：
 * 创建时间：2016/11/18 9:57
 */
public class BaseEntity {

    private static final String TAG = "【BaseEntity】";

    private BaseEntityType entityType;

    public enum BaseEntityType {

        COMMON_CONTENT_IMG,

        COMMON_CONTENT_ADD_IMG,
    }

    public BaseEntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(BaseEntityType entityType) {
        this.entityType = entityType;
    }
}
