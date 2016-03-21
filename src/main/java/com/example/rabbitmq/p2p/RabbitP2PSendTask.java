//package com.example.rabbitmq.p2p;
//
//import com.example.rabbitmq.RabbitVO;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//
///**
// * Created by liuhui on 2016/2/29.
// * 点对点模式
// */
//@EnableScheduling
//@Component
//@Configuration
//public class RabbitP2PSendTask {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private Queue queue;
//
//    @Bean
//    public Queue hello() {
//        return new Queue("tut.hello");
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void sendMessage(){
//        RabbitVO rabbitVO = new RabbitVO();
//        String[] data = new String[]{"1","2","3","4","5","6","7","8","9"};
//        rabbitVO.setId(data[new Random().nextInt(9)]);
//        rabbitVO.setDesc("测试");
//        this.rabbitTemplate.convertAndSend(queue.getName(), rabbitVO);
//    }
//}
//
