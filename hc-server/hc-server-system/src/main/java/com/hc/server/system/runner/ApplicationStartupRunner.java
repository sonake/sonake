package com.hc.server.system.runner;

import com.hc.common.bean.system.DataAccess;
import com.hc.common.service.RedisService;
import com.hc.server.system.service.IDataAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/1/3 11:40
 * @description：
 * @version:
 */
@Component
@Slf4j
public class ApplicationStartupRunner implements ApplicationRunner {
    @Autowired
    private IDataAccessService dataAccessService;
    @Autowired
    private RedisService redisService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<DataAccess> list = dataAccessService.list();
        list.forEach(dataAccess -> {
            redisService.set(dataAccess.getAccessResource(),dataAccess.getAccessResourceField());
        });
        log.info("数据权限信息同步至redis完毕");
    }
}
