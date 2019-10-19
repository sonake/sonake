package com.hc.server.system;

import com.hc.common.annotation.HcCloudApplication;
import com.hc.server.system.properties.HcSwaggerProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@HcCloudApplication
@EnableTransactionManagement
@EnableFeignClients
@MapperScan("com.hc.server.system.mapper")
@EnableConfigurationProperties({HcSwaggerProperties.class})
public class HcServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcServerSystemApplication.class, args);
    }

}
