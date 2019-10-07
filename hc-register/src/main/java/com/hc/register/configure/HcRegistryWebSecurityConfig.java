package com.hc.register.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 该配置类用于开启Eureka服务端端点保护。
 */
@EnableWebSecurity
public class HcRegistryWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(httpSecurity);
    }
}
