package com.wx.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

/**
 * swagger2配置
 * @author wan
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${wx.version}")
    private String serviceVersion;

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wx"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Timestamp.class, Long.class);
    }

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("wx:service-" + serviceName)
                .version(serviceVersion)
                .description("api 描述")
                .build();
    }

}
