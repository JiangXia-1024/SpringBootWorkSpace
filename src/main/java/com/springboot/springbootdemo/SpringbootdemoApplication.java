package com.springboot.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.springbootdemo.dao")
public class SpringbootdemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootdemoApplication.class);
    public static void main(String[] args) {
        logger.info("===============项目启动了===============");
        SpringApplication.run(SpringbootdemoApplication.class, args);
        logger.info("===============启动成功了===============");
    }

}
