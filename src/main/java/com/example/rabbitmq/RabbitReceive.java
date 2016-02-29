//package com.example.rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//
///**
// * Created by liuhui on 2016/2/19.
// */
//@RabbitListener(queues = "#{queue1.name}")
//@Component
//@Configuration
//public class RabbitReceive {
//
//    @Bean
//    public FanoutExchange fanout() {
//        return new FanoutExchange("tut.fanout");
//    }
//
//    @Bean
//    public Queue queue1() {
//        return new AnonymousQueue();
//    }
//
//    @Bean
//    public Binding binding1(FanoutExchange fanout, Queue queue1) {
//        return BindingBuilder.bind(queue1).to(fanout);
//    }
//
//    @RabbitHandler
//    public void process(RabbitVO rabbitVO){
//        System.out.println("Consumer1:" + rabbitVO);
//    }
//}
