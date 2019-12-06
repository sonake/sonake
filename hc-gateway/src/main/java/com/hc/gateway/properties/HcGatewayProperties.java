package com.hc.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/6 13:41
 * @description：
 * @version: 1.0
 */
@RefreshScope
@Data
@Configuration
@ConfigurationProperties(prefix = "hc.gateway")
public class HcGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
