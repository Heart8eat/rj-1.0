package com.rj.backendjixian.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Swagger3Config {
    @Bean
    public OpenAPI createApi() {
        return new OpenAPI().info(getInfo());
    }

    // 创建元数据
    private Info getInfo() {
        return new Info()
                // 标题
                .title("SpringDoc 接口文档")
                // 简短描述
                .description("基于OpenAPI 3的API文档生成工具")
                // 公开的API的联系人信息
                .contact(createCon())
                // API文档的版本信息
                .version("0.1");
    }

    // 创建公开的API的联系人信息
    private Contact createCon() {
        return new Contact();
    }

    @Bean
    public GroupedOpenApi buyerApi() {
        return GroupedOpenApi.builder()
                .group("买家接口")
                .pathsToMatch("/buyers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder()
                .group("其他接口")
                .pathsToMatch("/**")
                .build();
    }

}
