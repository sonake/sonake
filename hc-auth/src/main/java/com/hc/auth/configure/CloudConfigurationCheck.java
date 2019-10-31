package com.hc.auth.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.client.ConfigServerInstanceProvider;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/10 17:46
 * @description：检查是否拉取配置中心文件(Hc_Auth配置中心)
 * @version: 1.0
 */
@Slf4j
//@Configuration
//@Conditional(CloudConfigurationCheck.InnerCondition.class)
public class CloudConfigurationCheck implements InitializingBean {

    @Autowired(required = false)
    ConfigServerInstanceProvider provider;

    @Value("${spring.cloud.config.discovery.service-id:hc-config}")
    String serviceName;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (provider == null) {
            throw new BeanCreationException("config server error --spring.native-boot.enabled=true 作为启动参数");
        }
        try {
            provider.getConfigServerInstances(serviceName);
        } catch (IllegalStateException e) {
            throw new BeanCreationException(" config server error，强制脱离配置中心请使用 --spring.native-boot.enabled=true 作为启动参数",e);
        }
        log.info("使用远程文件！");
    }

    public static class InnerCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return !context.getEnvironment().containsProperty("spring.native-boot.enabled")||context.getEnvironment().getProperty("spring.native-boot.enabled","false").equals("false");
        }
    }
}
