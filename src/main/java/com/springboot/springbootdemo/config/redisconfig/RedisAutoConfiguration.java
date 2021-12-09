package com.springboot.springbootdemo.config.redisconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * RedisTemplate的自动配置类
 */
public class RedisAutoConfiguration {
    /***
     * @Description: RedisTemplate使用的是JdkSerializationRedisSerializer,存入数据会将数据先序列化成字节数组然后在存入Redis数据库
     * @Param: [redisConnectionFactory]
     * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.Object,java.lang.Object>
     * @Date: 2021-11-26
     */

    @Bean
    /***
     * @Description: ConditionalOnMissingBean注解是修饰bean的一个注解，主要实现的是，当bean被注册之后，如果而注册相同类型的bean，就不会成功，
     *               它会保证你的bean只有一个，即实例只有一个，当注册多个相同的bean时，会出现异常，以此来告诉开发人员。
     * @Param: [redisConnectionFactory]
     * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.Object,java.lang.Object>
     * @Date: 2021-11-26
     */
    @ConditionalOnMissingBean(name="redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /***
     * @Description: StringRedisTemplate继承RedisTemplate，但是两者的数据是不共通的
     * StringRedisTemplate使用的是StringRedisSerializer
     * @Param: [redisConnectionFactory]
     * @return: org.springframework.data.redis.core.StringRedisTemplate
     * @Author: raven.jiang
     * @Date: 2021-11-26
     */

    @Bean
    @ConditionalOnMissingBean(name="stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
