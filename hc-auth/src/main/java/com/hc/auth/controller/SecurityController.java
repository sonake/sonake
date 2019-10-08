package com.hc.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import exception.HcException;
import result.Ret;
import result.Rets;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 11:25
 * @description：
 * @version:
 */
@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public Ret signout(HttpServletRequest request) throws HcException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        Ret r = new Ret();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new HcException("退出登录失败");
        }
        return Rets.success("退出登录成功");
    }

}
