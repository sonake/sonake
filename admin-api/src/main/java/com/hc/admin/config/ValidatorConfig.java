package com.hc.admin.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ：xzyuan
 * @date ：Created in 2019/6/21 9:19
 * @description：参数校验配置,当参数很多时，
 * 只要校验到错误信息就返回，不需要校验完所有参数返回
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory= Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        return  validator;
    }
}
