//package com.hc.server.system.configure;
//
//
//import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Signature;
//
//import java.sql.Connection;
//import java.util.Properties;
//
//@Intercepts({@Signature(
//        type = StatementHandler.class,
//        method = "prepare",
//        args = {Connection.class, Integer.class}
//)})
//public class DataPermissionInterceptor extends AbstractSqlParserHandler implements Interceptor {
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        return null;
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return null;
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}
