package com.hc.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 9:25
 * @description：mybatis-plus分页配置
 * @version: 1.0
 */
@Configuration
public class HcDatasourceConfig {
    @Bean
    @Order(2)
    public DataAccessInterceptor dataPermissionInterceptor(){
        return new DataAccessInterceptor();
    };
    @Bean
    @Order(1)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }
}
