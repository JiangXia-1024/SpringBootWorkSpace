package com.springboot.springbootdemo.config.myconfig;


import ch.qos.logback.core.db.DBHelper;
import com.springboot.springbootdemo.bean.Car;
import com.springboot.springbootdemo.bean.PropertiesBean;
import com.springboot.springbootdemo.bean.User;
import com.springboot.springbootdemo.bean.YamlDean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.UUID;
//@Configuration(proxyBeanMethods = false) //告诉SpringBoot这是一个配置类 == 配置文件
//@Configuration  //配置注解：告诉Springboot这是一个配置类，效果等同于配置文件
//@Configuration(proxyBeanMethods = true) 则返回true
//@Configuration(proxyBeanMethods = false),则返回false
/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 *    Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *    Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *
 *    proxyBeanMethods = true是Full模式:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象，组件之间有依赖关系用full模式
 *    proxyBeanMethods = true是Lite模式：Lite模式减少判断，加速容器启动过程，组件之间无依赖关系用Lite模式
 *
 *   组件依赖必须使用Full模式默认。其他默认是否Lite模式
 *
 *   @Import({User.class, DBHelper.class})：给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
 */
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)  //true是默认值，设置为false，则启动快
//@ConditionalOnBean(name="user")
@ConditionalOnMissingBean(name="yml")
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    @Bean //给容器中添加组件，以方法名作为组件的id，返回类型就是组件的类型，返回的值，就是组件在容器中的实例
    public PropertiesBean user01(){
        PropertiesBean user = new PropertiesBean("张三",24);
        return user;
    }

    @Bean("yml")
    public YamlDean yamlbean(){
        return new YamlDean(UUID.randomUUID().toString(),"tomcat",21,"11520@qq.com","gfgfgf",user01());
    }

}
