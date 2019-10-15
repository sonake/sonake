package com.hc.common.bean;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 13:23
 * @description：正则表达式常量
 * @version: 1.0
 */
public class RegexpConstant {
    // 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
    public static final String MOBILE_REG = "[1]\\d{10}";
}
