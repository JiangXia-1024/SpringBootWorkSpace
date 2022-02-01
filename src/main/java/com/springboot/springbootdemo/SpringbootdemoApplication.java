package com.springboot.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.springboot.springbootdemo.dao")
//@EnableCaching 开启基于注解的缓存
//标注缓存注解：
// @Cacheable：主要针对方法的配置，能够根据方法的请求参数对其结果进行缓存
// @CacheEvict:清空缓存
// @CachePut：保证方法被调用，又希望结果被缓存
@EnableCaching
/**
 * 
 * @author jiangxia
 * @date 2021/12/12 21:45
 * @param No such property: code for class: Script1 
 * @return No such property: code for class: Script1
 * @description: Rabbitmq自动配置类：RabbitAutoconfiguration
 * 有自定配置了连接工厂：ConnectionFactory
 * RabbitProperties 封装了RabbitMQ的配置
 * RabbitTemplate：给RabbitMQ发动和接受消息
 * AMqpAdmin：RabbitMQ系统的管理功能组件：创建和删除queue、exchange、Binding
 *
 */
@EnableRabbit  //开启基于注解的rabbitmq EnableRabbit+RabbitListener监听消息队列的内容
@EnableAsync  //开启异步任务注解
@EnableScheduling //开启定时任务注解
public class SpringbootdemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootdemoApplication.class);
    public static void main(String[] args) {
        logger.info("===============项目启动了===============");
        SpringApplication.run(SpringbootdemoApplication.class, args);
        logger.info("===============启动成功了===============");
    }

}
