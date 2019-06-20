package com.hc.admin.common;

import com.hc.admin.annotion.ParamValid;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/20 14:17
 * @description：异常信息
 * @version: 1.0
 */
public enum  ValidatorEnum implements ConstraintValidator<ParamValid,Object> {
    ;

    @Override
    public void initialize(ParamValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
