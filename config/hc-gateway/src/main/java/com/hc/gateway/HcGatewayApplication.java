package com.hc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class HcGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcGatewayApplication.class, args);
    }

}
