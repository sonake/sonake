package com.hc.common.annotation;

import com.hc.common.configure.HcLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 15:42
 * @description：lettuce驱动注解
 * @version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HcLettuceRedisConfigure.class)
public @interface EnableHcLettuceRedis {
}
