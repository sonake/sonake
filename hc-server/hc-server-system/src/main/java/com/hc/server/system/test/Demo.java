package com.hc.server.system.test;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    private static Map getLetterNumber(char [] insertArr){

        // 插入完成的数组
        char [] endArr = {};

        // 需要返回的字母及字幕出现的次数
        Map<String,Integer> letterNumber = new HashMap<>();

        for(char i=0;i<insertArr.length;i++){
            String key = String.valueOf(insertArr[i]).toLowerCase();
            //            // 若key存在，将原来的value+1
            if(letterNumber.containsKey (key)){

                Integer value = letterNumber.get(key);
                letterNumber.put(key,value+1);
            }else {
                // 不存在则出现一次
                letterNumber.put(key,1);
            }
        }
        return letterNumber;
    }

    public static void main(String[] args) {
        // 预备插入的字母元素
        char [] insertArr = {};
        getLetterNumber(insertArr);
    }
}
