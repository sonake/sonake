package com.hc.common.annotation;

import com.hc.common.configure.HcAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/11 16:55
 * @description：注解驱动
 * @version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({HcAuthExceptionConfigure.class})
public @interface EnableHcAuthExceptionHandler {
}
