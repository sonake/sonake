package com.hc.auth.configure;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/11/25 16:38
 * @description：
 * @version:
 */
public class sss implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        return null;
    }
}
