package com.hc.server.system.configure;

import com.hc.server.system.properties.HcSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    private HcSwaggerProperties properties;

    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(securityScheme(properties)))
                .securityContexts(Collections.singletonList(securityContext(properties)));
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                properties.getTitle(),
                properties.getDescription(),
                properties.getVersion(),
                null,
                new Contact(properties.getAuthor(),properties.getUrl(),properties.getEmail()),
                properties.getLicense(),properties.getLicenseUrl(), Collections.emptyList()
        );
    }

    private SecurityScheme securityScheme(HcSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());

        return new OAuthBuilder()
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private SecurityContext securityContext(HcSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(HcSwaggerProperties swagger) {
        return new AuthorizationScope[]{
                new AuthorizationScope(swagger.getScope(), "")
        };
    }
}
