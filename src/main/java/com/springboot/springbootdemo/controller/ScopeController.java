package com.springboot.springbootdemo.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
public class ScopeController {

    public int number = 0;

    @RequestMapping("/text1")
    public String test1(){
        number=number+1;
        return String.valueOf(number);
    }

    @RequestMapping("/text2")
    public String test2(){
        number=number+1;

        return String.valueOf(number);
    }
}
