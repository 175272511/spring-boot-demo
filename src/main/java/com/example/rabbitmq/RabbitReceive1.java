package com.example.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by liuhui on 2016/8/12.
 */
@Component
@Configuration
@RabbitListener(queues = "#{cpTrackLogQueue.name}", containerFactory="rabbitListenerContainerFactory")
public class RabbitReceive1 {

    @Bean
    public Queue cpTrackLogQueue() {
        return new Queue("track.log.cp");
    }

    @Bean
    public TopicExchange cpTrackLogTopicExchange(){
        return new TopicExchange("track.log", true, false);
    }

    @Bean
    public Binding binding(TopicExchange cpTrackLogTopicExchange, Queue cpTrackLogQueue) {
        return BindingBuilder.bind(cpTrackLogQueue).to(cpTrackLogTopicExchange).with("save");
    }

    @RabbitHandler
    public void process(@Payload Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println(obj);
    }

}
