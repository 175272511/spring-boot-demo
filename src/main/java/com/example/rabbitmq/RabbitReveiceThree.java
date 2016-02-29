//package com.example.rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * Created by liuhui on 2016/2/23.
// */
//@RabbitListener(queues = "#{queue3.name}")
//@Component
//@Configuration
//public class RabbitReveiceThree {
//
//    @Bean
//    public FanoutExchange fanout() {
//        return new FanoutExchange("tut.fanout");
//    }
//
//    @Bean
//    public Queue queue3() {
//        return new AnonymousQueue();
//    }
//
//    @Bean
//    public Binding binding3(FanoutExchange fanout, Queue queue3) {
//        return BindingBuilder.bind(queue3).to(fanout);
//    }
//
//    @RabbitHandler
//    public void process(RabbitVO rabbitVO){
//        System.out.println("Consumer3:" + rabbitVO);
//    }
//}
