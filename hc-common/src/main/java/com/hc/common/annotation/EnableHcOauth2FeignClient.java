package com.hc.common.annotation;

import com.hc.common.configure.HcOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 11:22
 * @description：
 * @version:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HcOAuth2FeignConfigure.class)
public @interface EnableHcOauth2FeignClient {
}
