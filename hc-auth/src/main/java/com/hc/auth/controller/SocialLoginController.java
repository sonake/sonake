package com.hc.auth.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 社交登录相关
 */
@Slf4j
@RestController
@RequestMapping("/social")
public class SocialLoginController {
    private String clientId = "2d0ee0978235dbfa19cd";
    private String clientSecret = "cb0d90ba0b2c0a566c58a043df18c71ad1b04367";
    private String authorizeUrl = "https://github.com/login/oauth/authorize";
    private String redirectUrl = "http://127.0.0.1:9030/auth/social/callback";
    private String accessTokenUrl = "https://github.com/login/oauth/access_token";
    private String userInfoUrl = "https://api.github.com/user";



    @PostMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize("hccl"+AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest();
        return authRequest.login(callback);
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUrl)
                .build());
    }
}
