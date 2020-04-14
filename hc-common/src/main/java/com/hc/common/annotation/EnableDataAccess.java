package com.hc.common.annotation;


import java.lang.annotation.*;


/**
 * 数据权限开启
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableDataAccess {
    boolean enable() default true;
}
