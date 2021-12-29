package com.springboot.springbootdemo;

import com.springboot.springbootdemo.bean.Department;
import com.springboot.springbootdemo.bean.User;
import com.springboot.springbootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootdemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    //自定义的redis序列化器
    RedisTemplate<Object,User> userRedisTemplate;

    //RabbitTemplate：给RabbitMQ发动和接受消息
    @Autowired
    RabbitTemplate rabbitTemplate;

    //AMqpAdmin：RabbitMQ系统的管理功能组件：创建和删除queue、exchange、Binding
    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
    }

    //测试redis如何保存对象
    /**
     *
     * @author jiangxia
     * @date 2021/12/11 11:22
     * @param
     * @return No such property: code for class: Script1
     * @description redis原理：
     * cachemanager===cache缓存组件来实际给缓存中存取数据
     * 引入redis的starter后，容器中保存了rediscachemanager；rediscachemanager创建了rediscache缓存来作为缓存组件
     * rediscache通过操作redis来缓存数据
     * 默认保存数据 k-v都是obj对象，利用序列化保存
     * 保存为jason：
     * 1、引入redis的starter后，cachemanager变为rediscachemannager
     * 2、默认创建的rediscachemannager操作redis的时候使用的是redistemlate<obj><obj>
     * 3、redistemplate<obj><obj>是默认使用jdk的序列化机制
     * 自定义cachemanager
     *
     */
    @Test
    public void test01(){
        User user = userService.findUserByName("李青");
        //默认如果保存对象，使用jdk序列机制，序列化后保存到redis缓存，即类集成Seriveriable
        //redisTemplate.opsForValue().set("user",user); 会乱码
        //1.将数据依jason的方式保存（a、自己将对象转成jason；b、redistemplate默认的序列化规则。可以改变默认规则，自定义序列化器）
        userRedisTemplate.opsForValue().set("user",user);
    }

    /**
     *
     * @author jiangxia
     * @date 2021/12/12 17:00
     * @param
     * @return No such property: code for class: Script1
     * @description: 单播 （点对点） 发送消息
     */
    @Test
    public void test02(){
        //send参数：交换器、路由键、消息。 消息message需要自己构造一个 定制消息体内容和消息头
        //rabbitTemplate.send(exchange,routekey,object);
        //object默认当成消息体只需要传入要发送的对象，自动序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchange,routekey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("hrgr",1212,true,"gfgf"));
        //对象被默认序列化后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","jiang.news",map);
    }
    
    /**
     * 
     * @author jiangxia
     * @date 2021/12/12 17:16
     * @param
     * @return No such property: code for class: Script1
     * @description：接受消息
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("jiang.new");
        System.out.println(o);
        System.out.println(o.getClass());
    }

    /**
     *
     * @author jiangxia
     * @date 2021/12/12 21:31
     * @param
     * @return No such property: code for class: Script1
     * @description:广播 广播无需管路由键
     */
    @Test
    public void test03(){
        rabbitTemplate.convertAndSend("exchange.fanout",new Department(1,"交警大队"));
    }

    @Test
    public void creteExchange(){
        System.out.println("创建交换器");
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建队列");
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        System.out.println("创建绑定");
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqpadmin.haha",null));

    }
}
