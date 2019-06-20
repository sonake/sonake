package com.hc.admin.annotion;

import com.hc.admin.common.ValidatorEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/20 12:52
 * @description：请求参数统一交验
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorEnum.class)
public @interface ParamValid {
    //错误编码
    String classname() default "demo";
    //错误信息
    String msg() default  "error";

    //Object obj() default null;
}
