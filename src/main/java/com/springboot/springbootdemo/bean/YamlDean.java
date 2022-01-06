package com.springboot.springbootdemo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

@ConfigurationProperties(prefix = "my")
@Component
public class YamlDean {

    private String uuid;
    private String name;
    private int age;
    private String email;
    private String greeting;
    private PropertiesBean propertiesBean;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public PropertiesBean getPropertiesBean() {
        return propertiesBean;
    }

    public void setPropertiesBean(PropertiesBean propertiesBean) {
        this.propertiesBean = propertiesBean;
    }

    public YamlDean() {
    }

    public YamlDean(String uuid, String name, int age, String email, String greeting, PropertiesBean propertiesBean) {
        this.uuid = uuid;
        this.name = name;
        this.age = age;
        this.email = email;
        this.greeting = greeting;
        this.propertiesBean = propertiesBean;
    }
}
