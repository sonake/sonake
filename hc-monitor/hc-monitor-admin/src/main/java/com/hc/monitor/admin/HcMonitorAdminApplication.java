package com.hc.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class HcMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HcMonitorAdminApplication.class, args);
    }

}
