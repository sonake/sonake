package com.hc.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/5/6 17:19
 * @description：分页插件
 * @version: 1.0
 */
@Configuration
//@MapperScan("cn.nis.ntc.dao.system")
public class MybatisPlusConfig {
    /**
     * 分页插件的拦截
     * @return
     */
    @Bean
    public BetterPaginationInterceptor paginationInterceptor() {
        return new BetterPaginationInterceptor();

    }
}
