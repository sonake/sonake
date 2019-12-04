package com.hc.gateway.filter;

import com.hc.common.result.Ret;
import com.hc.common.result.Rets;
import com.hc.common.utils.ToolUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/12/4 9:51
 * @description：自定义Zuul异常处理
 * @version: 1.0
 */
@Slf4j
@Component
public class HcGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            Ret ret = resolveExceptionMessage(message, serviceId);

            HttpServletResponse response = ctx.getResponse();
            ToolUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ret
            );
            log.error("Zull sendError：{}", ret.getMsg());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private Ret resolveExceptionMessage(String message, String serviceId) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return Rets.failure("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return Rets.failure(serviceId + "服务不可用");
        }
        return Rets.failure("Zuul请求" + serviceId + "服务异常");
    }
}
