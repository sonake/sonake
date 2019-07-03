package com.hc.admin.annotion;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/21 10:01
 * @description：参数校验异常统一处理
 * todo 可以扩展自己的校验注解，例如多字段关联交验，与数据库已有数据的校验
 * @version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,METHOD})
@Documented
@Inherited
public @interface ParamValidator {
    String classname() default "";

   /* @Target({TYPE,METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List{
        ParamValidator[] value();
    }*/
}
