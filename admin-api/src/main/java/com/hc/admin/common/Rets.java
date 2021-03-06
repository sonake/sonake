package com.hc.admin.common;


public class Rets {

//    public static final Integer SUCCESS = 200;
//    public static final Integer FAILURE = 999;
//    public static  final Integer TOKEN_EXPIRE=50014;



    public static Ret success(Object data) {
        return new Ret(Code.C200.getCode(), Code.C200.getDesc(), data);
    }

    public static Ret failure(String msg) {
        return new Ret(Code.C999.getCode(), msg, null);
    }

    public static Ret success() {
        return new Ret(Code.C200.getCode(), Code.C200.getDesc(), null);
    }
    public static  Ret expire(){
        return new Ret(Code.C514.getCode(),Code.C514.getDesc(),null);
    }

    public static Ret failure(Integer code, String msg,Object data){
        return new Ret(code, msg, null);
    }
}
