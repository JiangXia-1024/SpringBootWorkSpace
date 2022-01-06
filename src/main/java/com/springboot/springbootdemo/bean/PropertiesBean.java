package com.springboot.springbootdemo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:test.properties")
@ConfigurationProperties(prefix = "com.jiangxia")
public class PropertiesBean {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PropertiesBean() {
    }

    public PropertiesBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
