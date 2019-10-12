package com.hc.hc.server.system.test.fallback;

import com.hc.hc.server.system.test.service.IHelloService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 9:57
 * @description：回退方法
 * @version: 1.0
 */
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
    @Override
    public IHelloService create(Throwable throwable) {
//        return new IHelloService() {
//            @Override
//            public String hello(String name) {
//                log.error("调用febs-server-system服务出错", throwable);
//                return "调用出错";
//            }
//        };
        return name -> {
            log.error("调用hc-server-system服务出错", throwable);
            return "调用出错";
        };
    }
}
