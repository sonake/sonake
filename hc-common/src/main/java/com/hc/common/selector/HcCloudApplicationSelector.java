package com.hc.common.selector;

import com.hc.common.configure.HcAuthExceptionConfigure;
import com.hc.common.configure.HcLettuceRedisConfigure;
import com.hc.common.configure.HcOAuth2FeignConfigure;
import com.hc.common.configure.HcServerProtectConfigure;
import com.hc.common.handler.HcAuthExceptionEntryPoint;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 14:54
 * @description：将这三个配置类一次性都注册到IOC容器中。在Spring中，要将多个类进行注册，可以使用selector的方式
 * @version: 1.0
 */
public class HcCloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                HcAuthExceptionConfigure.class.getName(),
                HcOAuth2FeignConfigure.class.getName(),
                HcServerProtectConfigure.class.getName(),
                HcLettuceRedisConfigure.class.getName()
        };
    }
}
