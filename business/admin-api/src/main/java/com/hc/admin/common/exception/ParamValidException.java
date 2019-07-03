package com.hc.admin.common.exception;

import lombok.Data;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/20 11:45
 * @description：参数校验异常
 * @version: 1.0
 */
@Data
public class ParamValidException extends RuntimeException{


    private String code;
    private String msg;

    public ParamValidException(String code, String msg) {
        this.msg = msg;
        this.code = code;


    }
}
