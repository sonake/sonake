package com.hc.common.result;

import com.hc.common.exception.HcException;


public class Rs {

//    public static final Integer SUCCESS = 200;
//    public static final Integer FAILURE = 999;
//    public static  final Integer TOKEN_EXPIRE=50014;


    public static R success(Object data) {
        return new R(Code.C200.getCode(), Code.C200.getDesc(), data);
    }

    public static R failure(String msg) {
        return new R(Code.C999.getCode(), msg, null);
    }

    public static R failure(HcException ntcEx) {
        return new R(ntcEx.getCode(), ntcEx.getMessage(), null);
    }

    public static R success() {
        return new R(Code.C200.getCode(), Code.C200.getDesc(), null);
    }


    public static R failure(Integer code, String msg, Object data) {
        return new R(code, msg, null);
    }

    public static R failure(Integer code, String msg) {
        return new R(code, msg, null);
    }
}
