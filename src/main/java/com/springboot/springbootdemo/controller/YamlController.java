package com.springboot.springbootdemo.controller;


import com.springboot.springbootdemo.bean.Person;
import com.springboot.springbootdemo.bean.PropertiesBean;
import com.springboot.springbootdemo.bean.YamlDean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({PropertiesBean.class, YamlDean.class})

public class YamlController {
    @Autowired
    private YamlDean yamlDean;

    @Autowired
    private Person person;

    @Value("${my.name}")
    private String name;

    @Value("${my.age}")
    private int age;

    @RequestMapping(value="/miya")
    public String miya(){
        return name+":"+age;
    }

    @RequestMapping(value = "himiya")
    public String himiya(){
        return yamlDean.getGreeting()+"====="+yamlDean.getUuid()+"======"+yamlDean.getName()+"====="+yamlDean.getAge()+"======="+yamlDean.getEmail();
    }

    @Autowired
    PropertiesBean propertiesBean;
    @RequestMapping(value = "/hipro")
    public String hipro(){
        return propertiesBean.getName()+"====="+propertiesBean.getAge();
    }

    @RequestMapping("/person")
    public Person returnPerson(){
        return  person;
    }

}
