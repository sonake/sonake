package selector;

import configure.HcAuthExceptionConfigure;
import configure.HcOAuth2FeignConfigure;
import configure.HcServerProtectConfigure;
import handler.HcAuthExceptionEntryPoint;
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
                HcServerProtectConfigure.class.getName()
        };
    }
}
