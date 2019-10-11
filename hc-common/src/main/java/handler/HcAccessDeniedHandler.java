package handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import result.Rets;
import utils.ToolUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/11 16:34
 * @description：用户无权限
 * @version: 1.0
 */
@Slf4j
public class HcAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ToolUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, Rets.failure("没有权限访问该资源"));
    }
}
