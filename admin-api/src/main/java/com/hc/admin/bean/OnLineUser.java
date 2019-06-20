package com.hc.admin.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hc.admin.common.utils.DateUtil;
import com.hc.admin.common.utils.HcUtil;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 在线用户
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OnLineUser implements Serializable {
    private static final long serialVersionUID = 2055229953429884344L;

    // 唯一编号
    private Long id = HcUtil.getNumber(RandomStringUtils.randomAlphanumeric(20));
    // 用户名
    private String username;
    // ip地址
    private String ip;
    // token(加密后)
    private String token;
    // 登录时间
    private String loginTime = DateUtil.formatFullTime(LocalDateTime.now(),DateUtil.FULL_TIME_SPLIT_PATTERN);
    // 登录地点
    private String loginAddress;
}
