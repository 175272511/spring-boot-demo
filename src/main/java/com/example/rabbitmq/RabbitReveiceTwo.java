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
//@RabbitListener(queues = "#{queue2.name}")
//@Component
//@Configuration
//public class RabbitReveiceTwo {
//
//    @Bean
//    public FanoutExchange fanout() {
//        return new FanoutExchange("tut.fanout");
//    }
//
//    @Bean
//    public Queue queue2() {
//        return new AnonymousQueue();
//    }
//
//    @Bean
//    public Binding binding2(FanoutExchange fanout, Queue queue2) {
//        return BindingBuilder.bind(queue2).to(fanout);
//    }
//
//    @RabbitHandler
//    public void process(RabbitVO rabbitVO){
//        System.out.println("Consumer2:" + rabbitVO);
//    }
//}
