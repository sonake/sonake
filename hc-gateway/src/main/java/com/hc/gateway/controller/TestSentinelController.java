package com.hc.gateway.controller;

import com.hc.common.exception.ValidateCodeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/10 15:47
 * @description：测试分布式卫兵限流
 * @version: 1.0
 */
@RestController
public class TestSentinelController {


    @GetMapping("sen")
    public String sen(){
        return "sentinel";
    }
}
