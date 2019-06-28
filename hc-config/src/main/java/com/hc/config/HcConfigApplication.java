package com.hc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * 配置中心服务
 */
@SpringCloudApplication
@EnableConfigServer
public class HcConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcConfigApplication.class, args);
    }

}
