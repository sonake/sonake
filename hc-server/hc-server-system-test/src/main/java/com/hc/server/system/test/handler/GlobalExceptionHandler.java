package com.hc.server.system.test.handler;

import com.hc.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/4 10:03
 * @description：异常捕获处理
 * @version: 1.0
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
