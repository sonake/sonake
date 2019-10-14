package com.hc.auth;

import com.hc.common.annotation.EnableHcAuthExceptionHandler;
import com.hc.common.annotation.EnableHcLettuceRedis;
import com.hc.common.annotation.EnableHcServerProtect;
import com.hc.auth.properties.HcAuthProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({HcAuthProperties.class})
@EnableHcAuthExceptionHandler
@EnableHcServerProtect
@MapperScan("com.hc.auth.mapper")
@EnableHcLettuceRedis
public class HcAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcAuthApplication.class, args);
    }

}
