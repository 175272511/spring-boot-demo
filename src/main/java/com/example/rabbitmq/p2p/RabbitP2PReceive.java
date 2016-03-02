package com.example.rabbitmq.p2p;


import com.example.rabbitmq.RabbitVO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuhui on 2016/2/19.
 */
@RabbitListener(queues = "#{queue}")
@Configuration
public class RabbitP2PReceive {

    @Bean
    public Queue queue() {
        return new Queue("tut.hello");
    }

    @RabbitHandler
    public void process(RabbitVO rabbitVO){
        System.out.println("Consumer1:" + rabbitVO);
    }
}
