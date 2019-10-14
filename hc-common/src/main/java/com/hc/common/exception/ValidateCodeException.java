package com.hc.common.exception;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 15:59
 * @description：验证码异常
 * @version: 1.0
 */
public class ValidateCodeException extends Exception{
    private static final long serialVersionUID = -1959328885258403315L;

    public ValidateCodeException(String message){
        super(message);
    }
}
