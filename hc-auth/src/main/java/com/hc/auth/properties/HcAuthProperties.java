package com.hc.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/10 11:12
 * @description：认证配置类
 * @version: 1.0
 */
@RefreshScope
@Data
@Configuration
@ConfigurationProperties(prefix = "hc.auth")
public class HcAuthProperties {
    private HcClientsProperties[] clients = {};
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;
}
