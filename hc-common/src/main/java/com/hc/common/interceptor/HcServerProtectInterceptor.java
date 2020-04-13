package com.hc.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hc.common.constant.HcConstant;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import com.hc.common.result.Rs;
import com.hc.common.utils.CommonTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 14:25
 * @description：全局拦截器拦截请求，并校验Zuul Token
 * @version: 1.0
 */
public class HcServerProtectInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //从请求头获取zuultoken
        String token = request.getHeader(HcConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(HcConstant.ZUUL_TOKEN_VALUE.getBytes()));
        if(CommonTools.equals(token,zuulToken)){
            return true;
        }else {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(Rs.failure("请通过网关获取资源")));
            return false;
        }
    }
}
