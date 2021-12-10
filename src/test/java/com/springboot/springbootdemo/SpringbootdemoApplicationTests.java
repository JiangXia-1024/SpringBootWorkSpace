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
    @Test
    public void test01(){
        User user = userService.findUserByName("李青");
        //默认如果保存对象，使用jdk序列机制，序列化后保存到redis缓存，即类集成Seriveriable
        //redisTemplate.opsForValue().set("user",user); 会乱码
        //1.将数据依jason的方式保存（a、自己将对象转成jason；b、redistemplate默认的序列化规则。可以改变默认规则，自定义序列化器）
        userRedisTemplate.opsForValue().set("user",user);
    }

}
