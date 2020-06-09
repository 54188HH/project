package com.lzq.springbootmybatis01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lzq.springbootmybatis01"})
public class SpringbootMybatis01Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatis01Application.class, args);
    }

}
