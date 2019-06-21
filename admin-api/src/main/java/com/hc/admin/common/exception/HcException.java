package com.hc.admin.common.exception;

import lombok.Data;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/18 18:47
 * @description：自定义异常类
 * @version: 1.0
 */
@Data
public class HcException extends Exception{
    private static final long serialVersionUID=-41181237483424574L;
    private String message;
    private String code;
    public HcException(String message) {
        super(message);
    }
}
