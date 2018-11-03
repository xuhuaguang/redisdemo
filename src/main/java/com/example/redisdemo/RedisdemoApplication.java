package com.example.redisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.redisdemo.dao")
//@ComponentScan(basePackages= {"com.example.redisdemo.controller"})
public class RedisdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisdemoApplication.class, args);
    }
}
