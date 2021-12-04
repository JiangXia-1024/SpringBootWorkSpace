package com.springboot.springbootdemo.controller;


import com.springboot.springbootdemo.bean.User;
import com.springboot.springbootdemo.service.UserService;
import com.springboot.springbootdemo.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/do/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean addUser(@RequestBody User user) {
        System.out.println("新增数据：");
        return userService.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean updateUser(@RequestBody User user) {
        System.out.println("更新数据：");
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam(value = "id", required = true) int Id) {
        System.out.println("删除数据：");
        return userService.deleteUser(Id);
    }

    //根据用户名查询数据
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        System.out.println("根据用户名查询数据：");
        //判断缓存是否存在
        boolean hasKey = redisUtil.exists(userName);
        if(hasKey){
            logger.info("==========通过redis缓存获取信息================");
            //获取缓存
            Object object = redisUtil.get(userName);
            return (User) object;
        }else{
            //查询数据库，并且存入缓存
            logger.info("==========通过数据库查询信息并且存入redis================");
            User user = userService.findUserByName(userName);
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtil.set(userName,user,10L, TimeUnit.MINUTES);
            return user;
        }
    }


    @RequestMapping(value = "/userAll", method = RequestMethod.GET)
    public List<User> findByUserAge() {
        System.out.println("查询所有数据:");
        return userService.findAll();
    }
}