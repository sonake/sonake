package com.hc.admin;

import io.swagger.models.auth.In;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/9/12 16:14
 * @description：
 * @version:
 */
public class XZ {
    public static void main(String[] args) {
        System.out.println(getInt());
    }



    public static int getInt(){
        int a=10;
        try {
            System.out.println(10/0);
            a=20;
        }catch (ArithmeticException e){
            a=30;
            return a;
        }finally {
            a=40;
            //return a;
        }
        return a;
    }
}
