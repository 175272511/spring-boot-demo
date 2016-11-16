package com.example.dubbo.service.impl;

import com.example.dubbo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by liuhui on 2016/7/20.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
