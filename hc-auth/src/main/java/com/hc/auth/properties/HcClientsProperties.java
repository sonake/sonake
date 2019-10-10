package com.hc.auth.properties;

import lombok.Data;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/10 11:11
 * @description：Client配置类
 * client对应client_id，secret对应client_secret，
 * grantType对应当前令牌支持的认证类型，scope对应认证范围。grantType和scope包含默认值。
 * @version: 1.0
 */
@Data
public class HcClientsProperties {
    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
