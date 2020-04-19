package com.hc.server.system.configure;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.hc.common.annotation.EnableDataAccess;
import com.hc.common.bean.CurrentUser;
import com.hc.common.bean.system.DataAccess;
import com.hc.common.bean.system.SysUser;
import com.hc.common.service.RedisService;
import com.hc.common.utils.CommonTools;
import com.hc.common.utils.HcUtils;
import com.hc.server.system.HcServerSystemApplication;
import com.hc.server.system.mapper.DataAccessMapper;
import com.hc.server.system.service.IDataAccessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringReader;
import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
@Slf4j
public class DataAccessInterceptor extends AbstractSqlParserHandler implements Interceptor {
    @Autowired
    RedisService redisService;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object paramObj = boundSql.getParameterObject();
        // 数据权限只针对查询语句
        if (SqlCommandType.SELECT == mappedStatement.getSqlCommandType()) {
            String originSql = boundSql.getSql();
            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(originSql));
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            Table fromItem = (Table) plainSelect.getFromItem();
            //判读是否对资源开启了数据权限管理
            String selectTableName = fromItem.getName();
            if (openDataAccess()&&openDataAccessResource(selectTableName)) {
                String id = mappedStatement.getId();
                log.info("\n 检测到系统开启数据权限过滤,对应方法为 -> {}",id);
                log.info("\n originSql -> {} ", originSql);
                String accessFiledValue = getSub();
                String dataPermissionSql = accessDataSql(originSql,accessFiledValue);
                metaObject.setValue("delegate.boundSql.sql", dataPermissionSql);
                log.info("\n dataPermissionSql -> {} ", dataPermissionSql);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 拼接数据过滤sql
     * @param originSql
     * @return
     */
    private String accessDataSql(String originSql,String accessFiledValue) {
        try {
            CurrentUser user = HcUtils.getCurrentUser();
            if (user == null) {
                return originSql;
            }
            String sub = "("+accessFiledValue+")";
            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(originSql));
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            Table fromItem = (Table) plainSelect.getFromItem();
            String selectTableName = fromItem.getAlias() == null ? fromItem.getName() : fromItem.getAlias().getName();
            // 获取数据权限关联字段
            String connectField = getField();
            String dataPermissionSql = selectTableName+"."+connectField+" in "+sub;
                    //String.format("%s."+connectField+" in '%s'", selectTableName, subDept);

            if (plainSelect.getWhere() == null) {
                plainSelect.setWhere(CCJSqlParserUtil.parseCondExpression(dataPermissionSql));
            } else {
                plainSelect.setWhere(new AndExpression(plainSelect.getWhere(), CCJSqlParserUtil.parseCondExpression(dataPermissionSql)));
            }
            return select.toString();
        } catch (Exception e) {
            log.warn("get data permission sql fail: {}", e.getMessage());
            return originSql;
        }
    }

    /**
     * 获取对应控制主体，当前用户的下属所有部门或地区
     */
    private String getSub(){
        Long userId = HcUtils.getCurrentUser().getId();
        //boolean s =  redisService.hasKey(selectTableName);
        String accessResources = (String) redisService.get("accessSubject");
        DataAccess dataAccess = JSON.parseObject(accessResources,DataAccess.class);
        if(dataAccess.getAccessSubject().equals("t_dept")){
            return redisService.get(userId+"subDept").toString();
        }else {
            return redisService.get(userId+"subArea").toString();
        }

    }

    /**
     * 获取对应资源与控制主体关联的字段
     */
    private String getField(){
        Long userId = HcUtils.getCurrentUser().getId();
        //boolean s =  redisService.hasKey(selectTableName);
        String accessResources = (String) redisService.get("accessSubject");
        DataAccess dataAccess = JSON.parseObject(accessResources,DataAccess.class);
        return dataAccess.getAccessResourceField().split(" ")[0];

    }


    /**
     * 根据有注解是否开启系统数据权限过滤
     * @param
     * @return
     */
    private Boolean openDataAccess() {
        EnableDataAccess dataAccess = null;
        try {
           //todo 如何获取main方法对应的类对象
            final Class<?> clazz =new  HcServerSystemApplication().getClass();
            if (clazz.isAnnotationPresent(EnableDataAccess.class)) {
                dataAccess = clazz.getAnnotation(EnableDataAccess.class);
            }
        } catch (Exception ignore) {
        }
        return dataAccess.enable();
    }

    /**
     * 判读是否对资源开启了数据权限管理
     * @return
     */
   private Boolean openDataAccessResource(String selectTableName){
       //Long userId = HcUtils.getCurrentUser().getId();
       //boolean s =  redisService.hasKey(selectTableName);
       String accessResources = (String) redisService.get("accessSubject");
       if(CommonTools.isNotEmpty(accessResources)){
           DataAccess dataAccess = JSON.parseObject(accessResources,DataAccess.class);
           String [] as = dataAccess.getAccessResource().split(",");
           return ArrayUtils.contains(as,selectTableName);
       }
       return false;
   }
   private Class getMainClass() {
       try {
           StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
           for (StackTraceElement stackTraceElement : stackTrace) {
               if ("main".equals(stackTraceElement.getMethodName())) {
                   return Class.forName(stackTraceElement.getClassName());
               }
           }
       }
       catch (ClassNotFoundException ex) {
           // Swallow and continue
       }
       return null;
   }
}
