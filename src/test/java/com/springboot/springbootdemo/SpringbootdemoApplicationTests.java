package com.springboot.springbootdemo;

import com.springboot.springbootdemo.bean.User;
import com.springboot.springbootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootdemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    //自定义的redis序列化器
    RedisTemplate<Object,User> userRedisTemplate;

    @Test
    void contextLoads() {
    }

    //测试redis如何保存对象
    /**
     *
     * @author jiangxia
     * @date 2021/12/11 11:22
     * @param No such property: code for class: Script1
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

}
