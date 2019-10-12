package annotation;

import configure.HcServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 14:44
 * @description：
 * @version:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HcServerProtectConfigure.class)
public @interface EnableHcServerProtect {
}
