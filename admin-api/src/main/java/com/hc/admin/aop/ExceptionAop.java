package com.hc.admin.aop;

import com.alibaba.fastjson.JSON;
import com.hc.admin.annotion.ParamValid;
import com.hc.admin.bean.User;
import com.hc.admin.common.utils.HcEnum;
import com.hc.admin.exception.ParamException;
import com.hc.admin.exception.RedisConnectException;
import com.hc.admin.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/20 12:56
 * @description：异常切面处理
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class ExceptionAop {
    @Autowired
    private RedisService redisService;

    @Before(value = "@annotation(com.hc.admin.annotion.ParamValid)")
    public void before(JoinPoint jp) throws Exception {
        doBefore(jp);
    }

    private void doBefore(JoinPoint jp) throws Exception {
        ParamValid paramValid=getParamValidate(jp);
        String classname=paramValid.classname();
        String[] redisKeyPrefix=classname.split("\\.");
        String excKey=redisKeyPrefix[redisKeyPrefix.length-1];
        //获取要校验的对象
        Object obj=Class.forName(classname).newInstance();
        //获取所有属性
        Field[] fields=obj.getClass().getDeclaredFields();
        Map<String,String> excMap=new HashMap<>(fields.length);
        Arrays.stream(fields).forEach(item -> {
            try {
                String[] fieldname=item.toString().split("\\.");
                String field=fieldname[fieldname.length-1];
                log.info(HcEnum.Hc_EXC.getValue()+"."+excKey+"."+field);
                String s=redisService.get(HcEnum.Hc_EXC.getValue()+"."+excKey+"."+field);
                if(StringUtils.isNotBlank(s)){
                    excMap.put(HcEnum.Hc_EXC.getValue()+"."+excKey+"."+field,s);
                }
            } catch (RedisConnectException e) {
                e.printStackTrace();
            }
        });
        if (getParamValidate(jp) == null) {return;}
        Object[] args = jp.getArgs();
        if (args == null) {return;}

        //将异常格式化成通用格式
        formateException(args);
    }

    private ParamValid getParamValidate(JoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(ParamValid.class);
    }

    private void formateException(Object[] args) throws Exception {
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result != null && result.getErrorCount() > 0) {
                    List<ObjectError> errors = result.getAllErrors();
                    String errorMsg = "";
                    for (ObjectError error : errors) {
                        if (error instanceof FieldError) {
                            FieldError fe = (FieldError) error;
                            errorMsg = String.format("%s:%s", fe.getField(), error.getDefaultMessage());
                        } else {
                            errorMsg = String.format("%s:%s ", error.getCode(), error.getDefaultMessage());
                        }
                        log.error(errorMsg);
                        throw new ParamException("999", errorMsg);
                    }
                }
            }
        }
    }

}
