//package com.example.rabbitmq;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageDeliveryMode;
//import org.springframework.amqp.core.MessagePostProcessor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//
///**
// */
//@EnableScheduling
//@Component
//public class RabbitSendTaskForDelay {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private FanoutExchange fanoutExchange;
//
//    @Bean
//    public FanoutExchange fanout() {
//        FanoutExchange fanoutExchange = new FanoutExchange("tut.fanout", true, false);
//        fanoutExchange.setDelayed(true);
//        return fanoutExchange;
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void sendMessage() throws Exception {
//        RabbitVO rabbitVO = new RabbitVO();
//        String[] data = new String[]{"1","2","3","4","5","6","7","8","9"};
//        rabbitVO.setId(data[new Random().nextInt(9)]);
//        rabbitVO.setDesc("测试");
//        final Map header = new HashMap();
//        header.put("x-delay", 10000);
//        System.out.println("发消息时间:" + System.currentTimeMillis() + "数据:" + rabbitVO.getId());
//        this.rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", rabbitVO,new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//                if(header!=null){
//                    Set<String> set = header.keySet();
//                    for(String key : set){
//                        message.getMessageProperties().setHeader(key,header.get(key));
//                    }
//                }
//
//                return message;
//            }
//        });
//
//
//    }
//}
