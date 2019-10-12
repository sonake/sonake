package com.hc.hc.server.system.test;

import annotation.HcCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@HcCloudApplication
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HcServerSystemTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcServerSystemTestApplication.class, args);
    }

}
