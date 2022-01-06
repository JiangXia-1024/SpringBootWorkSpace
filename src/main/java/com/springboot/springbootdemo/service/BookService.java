package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiangxia
 * @date 2022年01月06日 20:35
 */
//@Repository
//public class BookService{
//    @RabbitListener(queues = "amqpadmin.news")
//    public void receive01(Book book){
//        System.out.println("收到信息:" + book);
//    }
//
//    @RabbitListener(queues = "amqpadmin.queue")
//    public void receive02(Message message){
//        System.out.println(message.getBody());//拿到消息体
//        System.out.println(message.getMessageProperties());
//    }
//
//}
