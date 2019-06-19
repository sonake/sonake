package com.hc.admin.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.admin.common.Code;
import com.hc.admin.common.Rets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleException(Exception e) {
        String message=e.getLocalizedMessage();
        return Rets.failure(Code.C999.getCode(),message,null);
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e BindException
     * @return Object
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleConstraintViolationException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()+" ").append(error.getDefaultMessage()).append(StringPool.COMMA);
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return Rets.failure(Code.C999.getCode(),message.toString(),null);
    }


    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleUnauthorizedException(Exception e) {
        log.error("权限不足，{}", e.getMessage());
    }
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleExpiredSessionException(AuthenticationException e) {
        return Rets.failure(Code.C999.getCode(),e.getMessage(),null);
    }

}
