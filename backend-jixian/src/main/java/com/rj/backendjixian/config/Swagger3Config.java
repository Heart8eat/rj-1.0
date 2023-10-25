package com.rj.backendjixian.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        bearerFormat="JWT",
        name = "token",
        scheme = "bearer")
public class Swagger3Config {
    @Bean
    public OpenAPI createApi() {
        return new OpenAPI().info(getInfo());
    }

    // 创建元数据
    private Info getInfo() {
        return new Info()
                // 标题
                .title("\"果购\"————水果在线商城 SpringDoc 接口文档")
                // 简短描述
                .description("基于OpenAPI 3的API文档生成工具")
                // 公开的API的联系人信息
                .contact(createCon())
                // API文档的版本信息
                .version("0.1");
    }

    // 创建公开的API的联系人信息
    private Contact createCon() {
        return new Contact()
                .name("何涛 毛天航");
    }

    @Bean
    public GroupedOpenApi buyerApi() {
        return GroupedOpenApi.builder()
                .group("买家接口")
                .pathsToMatch("/buyers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi goodApi() {
        return GroupedOpenApi.builder()
                .group("商品接口")
                .pathsToMatch("/goods/**")
                .build();
    }

    @Bean
    public GroupedOpenApi merchantApi() {
        return GroupedOpenApi.builder()
                .group("商家接口")
                .pathsToMatch("/merchants/**")
                .build();
    }

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
                .group("订单接口")
                .pathsToMatch("/orders/**")
                .build();
    }

    @Bean
    public GroupedOpenApi shopApi() {
        return GroupedOpenApi.builder()
                .group("商铺接口")
                .pathsToMatch("/shops/**")
                .build();
    }


}
