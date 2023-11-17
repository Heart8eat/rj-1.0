package com.rj.backendjixian.config;

import com.rj.backendjixian.Interceptor.JwtInterceptor;
import com.rj.backendjixian.Interceptor.RolesAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    JwtInterceptor jwtInterceptor;
    @Autowired
    RolesAuthorizationInterceptor rolesAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .order(1);
        registry.addInterceptor(rolesAuthorizationInterceptor)
                .addPathPatterns("/**")
                .order(2);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+
                new ApplicationHome(this.getClass()).getDir().getAbsolutePath()+
                "/upload/");
    }
}
