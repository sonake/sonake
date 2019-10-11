package configure;

import handler.HcAccessDeniedHandler;
import handler.HcAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/11 16:40
 * @description：模块驱动注册bean
 * @version: 1.0
 */
public class HcAuthExceptionConfigure {
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public HcAccessDeniedHandler accessDeniedHandler() {
        return new HcAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public HcAuthExceptionEntryPoint authenticationEntryPoint() {
        return new HcAuthExceptionEntryPoint();
    }
}
