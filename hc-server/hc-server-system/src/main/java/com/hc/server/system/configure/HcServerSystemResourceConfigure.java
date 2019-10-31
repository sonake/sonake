package com.hc.server.system.configure;

import com.hc.server.system.properties.HcSwaggerProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 10:13
 * @description：资源过滤器,用于处理非/oauth/开头的请求，其主要用于资源的保护，
 * 客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源
 * @version: 1.0
 */
@Configuration
@EnableResourceServer
public class HcServerSystemResourceConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private HcSwaggerProperties hcSwaggerProperties;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(hcSwaggerProperties.getAnonUrl(),",");
        httpSecurity.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**")
                .authenticated();
    }
}
