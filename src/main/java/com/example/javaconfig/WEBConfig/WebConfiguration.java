package com.example.javaconfig.webconfig;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.IntrospectorCleanupListener;

/**
 * Created by liuhui on 2016/1/19.
 */
@Configuration
public class WebConfiguration {

    /**
     * 设置字符编码
     * @return
     */
    @Bean
    public FilterRegistrationBean encodingFilter(){
        return new FilterRegistrationBean(new CharacterEncodingFilter("UTF-8"));
    }

    /**
     * 防止spring内存溢出监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean introspectorCleanupListener(){
        return new ServletListenerRegistrationBean(new IntrospectorCleanupListener());
    }
}
