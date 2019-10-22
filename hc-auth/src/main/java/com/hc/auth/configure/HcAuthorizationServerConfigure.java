package com.hc.auth.configure;

import com.hc.auth.properties.HcAuthProperties;
import com.hc.auth.properties.HcClientsProperties;
import com.hc.auth.service.HcUserDetailService;
import com.hc.auth.translator.HcWebResponseExceptionTranslator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import com.hc.common.utils.ToolUtil;

import java.util.UUID;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 10:34
 * @description：安全配置类
 * @version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class HcAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private HcUserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HcAuthProperties hcAuthProperties;
    @Autowired
    private HcWebResponseExceptionTranslator exceptionTranslator;

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        HcClientsProperties[] hcClientsProperties=hcAuthProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clientDetailsServiceConfigurer.inMemory();
        if(ToolUtil.isNotEmpty(hcClientsProperties)){
            for (HcClientsProperties client : hcClientsProperties) {
                if (ToolUtil.isEmpty(client.getClient())) {
                    throw new Exception("client不能为空");
                }
                if (ToolUtil.isEmpty(client.getSecret())) {
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }

    }


    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 解决每次生成的 token都一样的问题
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }


    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(hcAuthProperties.getAccessTokenValiditySeconds());
        tokenServices.setRefreshTokenValiditySeconds(hcAuthProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }

    @Override
    @SuppressWarnings("all")
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer){
        authorizationServerEndpointsConfigurer
                .tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(exceptionTranslator);

    }

}
