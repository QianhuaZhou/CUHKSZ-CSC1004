package com.maben.websocket.config;

import com.maben.websocket.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class AdminLoginAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/message/**").excludePathPatterns("/user/login","/user/add");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WebSocketController/login")
                .addResourceLocations("classpath:/META-INF/resources/login.html");
        registry.addResourceHandler("/WebSocketController/register")
                .addResourceLocations("classpath:/META-INF/resources/register.html");
        registry.addResourceHandler("/img")
                .addResourceLocations("classpath:/META-INF/resources/img/**");
        registry.addResourceHandler("/css")
                .addResourceLocations("classpath:/META-INF/resources/css/**");
        registry.addResourceHandler("/js")
                .addResourceLocations("classpath:/META-INF/resources/js/**");
        super.addResourceHandlers(registry);
    }

}
