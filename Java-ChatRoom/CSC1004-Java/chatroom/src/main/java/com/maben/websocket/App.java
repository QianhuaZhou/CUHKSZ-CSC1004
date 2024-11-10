package com.maben.websocket;

import com.maben.websocket.interceptor.AdminLoginInterceptor;
import com.maben.websocket.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@SpringBootApplication //1
@MapperScan(basePackages = "com.meben.websocket")
@Slf4j(topic = "m.DyjcApplication")
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =SpringApplication.run(App.class,args);

        WebSocketServer.setApplicationContext(configurableApplicationContext);

    }

}
