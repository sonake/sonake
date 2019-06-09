package com.hc.admin.common;


import java.lang.annotation.*;

/**
 * 标记需要做业务日志的方法
 *
 * @author xzyuam
 * @date 2019-05-31 12:46
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HcLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"
     */
    String key() default "id";
}
