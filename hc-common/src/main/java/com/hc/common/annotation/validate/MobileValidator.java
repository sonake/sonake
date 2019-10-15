package com.hc.common.annotation.validate;

import com.hc.common.bean.RegexpConstant;
import com.hc.common.utils.ToolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 13:21
 * @description：自定义电话号验证
 * @version: 1.0
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return ToolUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
