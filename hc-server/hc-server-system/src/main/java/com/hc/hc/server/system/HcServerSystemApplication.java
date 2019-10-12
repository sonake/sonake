package com.hc.hc.server.system;

import annotation.HcCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@HcCloudApplication
public class HcServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcServerSystemApplication.class, args);
    }

}
