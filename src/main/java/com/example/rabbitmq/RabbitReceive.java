//package com.example.rabbitmq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//
///**
// * Created by liuhui on 2016/2/19.
// */
//@RabbitListener(queues = "foo")
//@Component
//public class RabbitReceive {
//
//    @RabbitHandler
//    public void process(RabbitVO rabbitVO){
//        System.out.println("Consumer1:" + rabbitVO);
//    }
//}
