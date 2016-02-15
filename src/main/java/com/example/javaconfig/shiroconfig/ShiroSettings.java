package com.example.javaconfig.shiroconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liuhui on 2016/2/5.
 */
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroSettings {

    private String loginUrl;
    //登录成功跳转地址
    private String successUrl;
    //登录失败跳转地址
    private String unauthorizedUrl;
    //过滤链定义
    private String filterChainDefinitions;
    //redis设置
    private RedisSettings redis;

    public RedisSettings getRedis() {
        return redis;
    }

    public void setRedis(RedisSettings redis) {
        this.redis = redis;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public String getFilterChainDefinitions() {
        return filterChainDefinitions;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    @Override
    public String toString() {
        return "ShiroSettings{" +
                "loginUrl='" + loginUrl + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", unauthorizedUrl='" + unauthorizedUrl + '\'' +
                ", filterChainDefinitions='" + filterChainDefinitions + '\'' +
                '}';
    }
}
