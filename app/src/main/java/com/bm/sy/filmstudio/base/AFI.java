package com.bm.sy.filmstudio.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by: zanjunrong
 * date: 2016/3/28
 * time: 9:51
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AFI {

    /**
     * 布局id
     */
    int contentViewId() default -1;

    /**
     * toolbar标题
     */
    int toolbarTitle() default -1;

    /**
     * toolbar标题
     */
    String toolbarTitleS() default "";
}
