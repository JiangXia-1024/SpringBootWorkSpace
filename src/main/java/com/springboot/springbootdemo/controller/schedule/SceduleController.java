package com.springboot.springbootdemo.controller.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author jiangxia
 * @date 2022年02月01日 16:07
 */
@RestController
public class SceduleController {

    @GetMapping("/say")
    @Scheduled(fixedDelay = 10000, initialDelay = 5000/*容器启动后延迟5秒后在执行一次定时器，以后每10秒再执行一次定时器*/)
    public String say(){
        System.out.println("你好"+System.currentTimeMillis());
        return "你好"+System.currentTimeMillis();
    }

    @GetMapping("/happynewyear")
    @Scheduled(cron = "0 18 16 * * ?"/*每天16点18执行一次*/)
    public String sayhappynewyear(){
        System.out.println("虎年大吉"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "虎年大吉"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
