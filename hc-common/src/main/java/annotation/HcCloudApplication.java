package annotation;

import org.springframework.context.annotation.Import;
import selector.HcCloudApplicationSelector;

import java.lang.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 14:57
 * @description：纳入
 * @EnableHcServerProtect，开启微服务防护，避免客户端绕过网关直接请求微服务；
 * @EnableHcOauth2FeignClient，开启带令牌的Feign请求，避免微服务内部调用出现401异常；
 * @EnableHcAuthExceptionHandler，认证类型异常翻译。
 * @version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HcCloudApplicationSelector.class)
public @interface HcCloudApplication {
}
