package com.example.javaconfig.webconfig;

import com.example.Interceptor.UserSecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by liuhui on 2016/1/22.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 重定向配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //重定向 localhost:8080/baidu 重定向至http://www.baidu.com
//        registry.addRedirectViewController("/baidu", "http://www.baidu.com");
        //指向页面, localhost:8080/index 指向 welcome.jsp
//        registry.addViewController("/index").setViewName("welcome");
    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/**");
    }

}
