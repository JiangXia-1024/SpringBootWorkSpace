package com.springboot.springbootdemo.controller.async;

import com.springboot.springbootdemo.service.aysnc.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangxia
 * @date 2022年02月01日 15:25
 */
@RestController
public class AsyncController {
    @Autowired
    AsyncService asyncService;

    @GetMapping("/async")
    public String asyncmethod1(){
        asyncService.aysncService();
        return "async success"+System.currentTimeMillis();
    }
}
