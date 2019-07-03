package com.hc.admin.config.prop;

import lombok.Data;

@Data
public class ShiroProperties {

    private String anonUrl;

    /**
     * token默认有效时间 1天
     */
    private Long tokenTimeOut = 86400L;

}
