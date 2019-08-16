package com.hc.admin.controller;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/7/10 16:16
 * @description：
 * @version:
 */
public class sss {
    /**
     *
     * @param args
     */
    /*

     */
    public static void main(String[] args) {
        int i = 1;
        int j = i++;
        if ((i == (++j)) && ((i++) == j)) {
            i += j;
        }
        System.out.println(i);
        int sum = 0;
        for (int c = 0; c < 10; c++) {
            sum += c;
            if (c % 3 == 0) {
                break;
            }
        }
        System.out.println(sum);
    }
}
