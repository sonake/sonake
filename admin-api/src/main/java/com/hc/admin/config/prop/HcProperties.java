package com.hc.admin.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/14 10:12
 * @description：自定义配置
 * @version: 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hc")
public class HcProperties {
    private ShiroProperties shiro = new ShiroProperties();
}
