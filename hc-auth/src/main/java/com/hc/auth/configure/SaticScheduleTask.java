package com.hc.auth.configure;

import com.alibaba.fastjson.JSON;
import com.hc.auth.properties.HcAuthProperties;
import com.hc.auth.properties.HcClientsProperties;
import com.hc.auth.properties.HcValidateCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/11 11:49
 * @description：输出认证中心的自定义配置内容
 * @version: 1.0
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    private HcAuthProperties hcAuthProperties;
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        HcClientsProperties[] hcClientsProperties=hcAuthProperties.getClients();
        HcValidateCodeProperties hcValidateCodeProperties = hcAuthProperties.getCode();
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        System.err.println("输出参数： "+ JSON.toJSONString(hcClientsProperties));
        System.err.println("输出参数： "+ JSON.toJSONString(hcValidateCodeProperties));
    }
}