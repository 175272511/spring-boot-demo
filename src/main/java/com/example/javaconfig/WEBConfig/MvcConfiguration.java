package com.example.javaconfig.WEBConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by liuhui on 2016/1/22.
 * 重定向配置
 * localhost:8080/baidu跳转至www.baidu.com
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //重定向
        registry.addRedirectViewController("/baidu", "http://www.baidu.com");
        //指向页面, localhost:8080/index 指向 welcome.jsp
        registry.addViewController("/index").setViewName("welcome");
    }


}
