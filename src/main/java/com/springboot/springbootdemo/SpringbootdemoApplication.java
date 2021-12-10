package com.springboot.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.springboot.springbootdemo.dao")
//@EnableCaching 开启基于注解的缓存
//标注缓存注解：
// @Cacheable：主要针对方法的配置，能够根据方法的请求参数对其结果进行缓存
// @CacheEvict:清空缓存
// @CachePut：保证方法被调用，又希望结果被缓存
@EnableCaching
public class SpringbootdemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootdemoApplication.class);
    public static void main(String[] args) {
        logger.info("===============项目启动了===============");
        SpringApplication.run(SpringbootdemoApplication.class, args);
        logger.info("===============启动成功了===============");
    }

}
