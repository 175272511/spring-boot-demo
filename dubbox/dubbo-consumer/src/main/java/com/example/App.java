package com.example;

import com.example.dubbo.service.HelloService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 *
 */
@Component
public class App {

    @Autowired
    private HelloService helloService;

    private boolean start;

    @Scheduled(fixedRate = 500)
    public void test(){
        if (!start){
            System.out.println("开始测试");
            start = true;
        }
        try {
            String name = helloService.sayHello(RandomStringUtils.randomAlphanumeric(5));
//            System.out.println(name);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
