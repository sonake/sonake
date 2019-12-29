package com.hc.common.result;

import lombok.Data;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 11:23
 * @description：响应结果
 * @version: 1.0
 */
@Data
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public R(){};

    public R(String msg) {
        this.msg=msg;

    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
