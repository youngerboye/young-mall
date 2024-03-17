package com.young.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan("com.young.mall.dao")
public class YoungMallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoungMallAdminApplication.class, args);
    }
}
