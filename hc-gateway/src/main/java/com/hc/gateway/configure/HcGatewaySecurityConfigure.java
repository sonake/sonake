package com.hc.gateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 14:05
 * @description：安全配置类：因为hc-gateway引入了hc-common模块，hc-common模块包含了Spring Cloud Security依赖，
 * 所以我们需要定义一个自己的WebSecurity配置类，来覆盖默认的。这里主要是关闭了csrf功能，否则会报csrf相关异常
 * @version: 1.0
 */
@EnableWebSecurity
public class HcGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests().antMatchers("/actuator/**").permitAll();
    }
}
