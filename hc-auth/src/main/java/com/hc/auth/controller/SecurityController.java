package com.hc.auth.controller;

import com.hc.auth.service.ValidateCodeService;
import com.hc.common.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hc.common.exception.HcException;
import com.hc.common.result.R;
import com.hc.common.result.Rs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private ValidateCodeService validateCodeService;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public R signout(HttpServletRequest request) throws HcException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        if (!consumerTokenServices.revokeToken(token)) {
            throw new HcException("退出登录失败");
        }
        return Rs.success("退出登录成功");
    }

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }
}
