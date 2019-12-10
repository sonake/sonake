package com.hc.common.handler;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.hc.common.result.Rets;
import com.hc.common.utils.ToolUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/11 16:15
 * @description：令牌错误
 * @version: 1.0
 */
public class HcAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ToolUtil.makeResponse(response,MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED,Rets.failure("token无效"));
    }
}
