package com.springboot.springbootdemo.service.aysnc;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author jiangxia
 * @date 2022年02月01日 15:21
 */
@Service
public class AsyncService {

    @Async //@Async注解来声明一个或多个异步任务，可以加在方法或者类上，加在类上表示这整个类都是使用这个自定义线程池进行操作
    public void aysncService(){
        System.out.println("今天是大年初一！"+System.currentTimeMillis());
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("祝大家虎年大吉！"+System.currentTimeMillis());
    }
}
