package com.hc.admin.aop;

import com.alibaba.fastjson.JSON;
import com.hc.admin.annotion.ParamValidator;
import com.hc.admin.bean.HcExc;
import com.hc.admin.common.exception.ParamValidException;
import com.hc.admin.common.utils.HcEnum;
import com.hc.admin.service.RedisService;
import lombok.extern.slf4j.Slf4j;
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
 * @description：异常切面处理,主要处理请求参数的信息，抛出自定义的code、msg
 * @version: 1.0
 */
@Slf4j
@Aspect
@Component
public class ParamExceptionAop {
    @Autowired
    private RedisService redisService;

    @Before(value = "@annotation(com.hc.admin.annotion.ParamValidator)")
    public void before(JoinPoint jp) throws Exception {
        doBefore(jp);
    }

    private void doBefore(JoinPoint jp) throws Exception {
        ParamValidator paramValid=getParamValidate(jp);
        String classname=paramValid.classname();
        String[] redisKeyPrefix=classname.split("\\.");
        String excKey=redisKeyPrefix[redisKeyPrefix.length-1];
        //获取要校验的对象
        Object obj=Class.forName(classname).newInstance();
        //获取所有属性
        Field[] fields=obj.getClass().getDeclaredFields();
        Map<String,String> excMap=new HashMap<>(fields.length);
        for (Field item : fields) {
                String[] fieldname = item.toString().split("\\.");
                String field = fieldname[fieldname.length - 1];
                String s = redisService.get(HcEnum.Hc_EXC.getValue() + "." + excKey + "." + field);
                if (StringUtils.isNotBlank(s)) {
                    excMap.put(HcEnum.Hc_EXC.getValue() + "." + excKey + "." + field, s);
                }
        }
        Object[] args = jp.getArgs();
        if (args == null) {return;}

        //将异常格式化成通用格式
        formateException(args,excMap,excKey);
    }

    private ParamValidator getParamValidate(JoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(ParamValidator.class);
    }

    public void formateException(Object[] args,Map<String,String> map,String exkey) {
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result != null && result.getErrorCount() > 0) {
                    List<ObjectError> errors = result.getAllErrors();
                    String code="999";
                    String errorMsg = "";
                    for (ObjectError error : errors) {
                        if (error instanceof FieldError) {
                            FieldError fe = (FieldError) error;
                            String msg=map.get(HcEnum.Hc_EXC.getValue()+"."+exkey+"."+fe.getField());
                            HcExc he= JSON.parseObject(msg,HcExc.class);
                            code=he.getCode();
                            errorMsg = String.format("%s:%s", fe.getField(), error.getDefaultMessage());
                            if(StringUtils.isNotBlank(he.getMsg())){
                                errorMsg=he.getMsg();
                            }
                        } else {
                            errorMsg = String.format("%s:%s ", error.getCode(), error.getDefaultMessage());
                        }
                        log.error(errorMsg);
                        throw new ParamValidException(code,errorMsg);
                    }
                }
            }
        }
    }

}
