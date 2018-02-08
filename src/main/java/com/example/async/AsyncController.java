package com.example.async;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 * User: peter.liu
 * Date: 2018/2/8
 * Description:
 */
@RestController
public class AsyncController {

    @RequestMapping("test")
    public WebAsyncTask test(){
        System.out.println(Thread.currentThread().getName());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //假设是一些长时间任务
                Thread.sleep(3000);
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getName());
                return "test";
            }
        };
        return new WebAsyncTask(callable);
    }

    @RequestMapping("test1")
    public String test1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(3000);
        return "test";
    }
}
