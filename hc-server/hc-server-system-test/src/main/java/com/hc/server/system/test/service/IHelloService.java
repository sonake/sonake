package com.hc.server.system.test.service;

import com.hc.server.system.test.fallback.HelloServiceFallback;
import com.hc.common.constant.HcServerConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 9:41
 * @description：定义system服务的接口
 * @version: 1.0
 */
@FeignClient(value = HcServerConstant.HC_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam String name);
}
