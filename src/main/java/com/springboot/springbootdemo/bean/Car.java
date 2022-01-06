package com.springboot.springbootdemo.bean;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 * 1、组合注解1：@Component+ConfigurationProperties：使用@Component注解将类注册到容器中，然后使用ConfigurationProperties注解读取配置文件中的配置
 * 2、@EnableConfigurationProperties+@ConfigurationProperties注解：EnableConfigurationProperties注解使用在配置类上，注释掉Component注解，加在myconfig上
 * @EnableConfigurationProperties(Car.class)
 * 只使用@ConfigurationProperties注解則啓動會報錯
 */
//@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String band;
    private Integer price;

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Car() {
    }

    public Car(String band, Integer price) {
        this.band = band;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "band='" + band + '\'' +
                ", price=" + price +
                '}';
    }
}

