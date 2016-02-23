//package com.example.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by liuhui on 2016/2/23.
// */
//@RabbitListener(queues = "foo")
//@Component
//public class RabbitReveiceThree {
//
//    @RabbitHandler
//    public void process(RabbitVO rabbitVO){
//        System.out.println("Consumer3:" + rabbitVO);
//    }
//}
