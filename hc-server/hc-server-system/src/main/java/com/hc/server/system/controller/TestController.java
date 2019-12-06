package com.hc.server.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/9 15:49
 * @description：测试服务
 * @version: 1.0
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("info")
    public String test(){

        return "hc-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }


    /**
     * 供远程调用
     * @param name
     * @return
     */
    @GetMapping("hello")
    public String hello(String name) {
        log.info("/hello服务被调用");
        return "hello" + name;
    }
}
