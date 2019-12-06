package com.hc.gateway.controller;

import com.hc.common.result.Ret;
import com.hc.common.result.Rets;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/6 14:37
 * @description：
 * @version: 1.0
 */
@RestController
public class FallbackController {
    @RequestMapping("fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Ret systemFallback(@PathVariable String name) {
        String response = String.format("访问%s超时或者服务不可用", name);
        return Rets.failure(response);
    }
}
