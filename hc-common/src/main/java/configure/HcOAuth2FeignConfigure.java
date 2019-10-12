package configure;

import constant.HcConstant;
import feign.RequestInterceptor;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/12 11:16
 * @description：拦截Feign请求，手动往请求头上加入令牌
 * @version: 1.0
 */
public class HcOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(HcConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(HcConstant.ZUUL_TOKEN_HEADER, zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if(details instanceof OAuth2AuthenticationDetails){
                String authorizationToken=((OAuth2AuthenticationDetails)details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION,"bearer "+authorizationToken);
            }
        };
    }
}
