package com.example.dubbo.service.impl;

import com.example.dubbo.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuhui on 2016/7/20.
 */
@Service
public class HelloServiceImpl implements HelloService {
    AtomicInteger num = new AtomicInteger();
    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num.incrementAndGet());
        return "hello " + name;
    }
}
