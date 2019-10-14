package com.hc.common.constant;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 14:09
 * @description：常量
 * @version: 1.0
 */
public class HcConstant {
    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    public static final String ZUUL_TOKEN_HEADER = "ZuulToken";
    /**
     * Zuul请求头TOKEN值
     */
    public static final String ZUUL_TOKEN_VALUE = "hc:zuul:123456";
    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "hc.captcha.";
}
