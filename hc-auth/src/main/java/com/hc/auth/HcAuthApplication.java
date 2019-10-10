package com.hc.auth;

import com.hc.auth.properties.HcAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({HcAuthProperties.class})
public class HcAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcAuthApplication.class, args);
    }

}
