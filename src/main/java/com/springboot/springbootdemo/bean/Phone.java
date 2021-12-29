package com.springboot.springbootdemo.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author jiangxia
 * @date 2021年12月29日 22:31
 */
@Data /*自动添加getter、setter方法，需要引入lombok依赖*/
@ToString
public class Phone {
    private String band;
    private Double price;
}