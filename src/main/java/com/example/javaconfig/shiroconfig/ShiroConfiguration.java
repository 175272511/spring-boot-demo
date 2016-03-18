package com.example.javaconfig.shiroconfig;

import com.example.javaconfig.druidconfig.DruidConfiguration;
import com.example.javaconfig.mybatisconfig.MybatisConfiguration;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.util.Collection;
import java.util.EnumSet;

/**
 * Created by liuhui on 2016/2/5.
 */
@Configuration
@AutoConfigureBefore({DruidConfiguration.class, MybatisConfiguration.class})
public class ShiroConfiguration {

    /**
     * shiro过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean authorizationFilter(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new DelegatingFilterProxy("shiroFilter"));//The filter-name matches name of a 'shiroFilter' bean inside spring beans
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        filterRegBean.addInitParameter("targetFilterLifecycle", "true");
        filterRegBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return filterRegBean;
    }

    /**
     * 过滤器配置
     * @param securityManager
     * @param settings
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterConfig(SecurityManager securityManager, ShiroSettings settings){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(settings.getLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(settings.getSuccessUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(settings.getUnauthorizedUrl());
        shiroFilterFactoryBean.setFilterChainDefinitions(settings.getFilterChainDefinitions());
        return shiroFilterFactoryBean;
    }

    /**
     * 会话DAO
     * @return
     */
    @Bean
    public RedisSessionDAO sessionDAO(RedisManager redisManager){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return  redisSessionDAO;
    }

    @Bean
    public SimpleCookie sessionIdCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(1800000);
        return simpleCookie;
    }

    /**
     * 配置集中式会话管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager(RedisSessionDAO sessionDAO, SimpleCookie simpleCookie){
        DefaultWebSessionManager defaultWebSessionManager = new MySessionManager();
        //会话的全局过期时间
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        //是否删除过期的会话
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        //是否开启会话验证调度器,默认开启
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //设置会话DAO
        defaultWebSessionManager.setSessionDAO(sessionDAO);
        //是否开启COOKIE验证调度器,默认开启
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        //设置COOKIE
        defaultWebSessionManager.setSessionIdCookie(simpleCookie);
        return defaultWebSessionManager;
    }

    /**
     * 安全管理器
     * @param realms
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(Collection<Realm> realms, SessionManager sessionManager,RedisCacheManager redisCacheManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealms(realms);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setCacheManager(redisCacheManager);
        return defaultWebSecurityManager;
    }

    /**
     * Shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 支持shiro权限注解
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    /**
     * 支持shiro权限注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro缓存配置
     * @param redisManager
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }
    /**
     * shiro缓存配置
     * @param shiroSettings
     * @return
     */
    @Bean
    public RedisManager redisManager(ShiroSettings shiroSettings){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(shiroSettings.getRedis().getHost());
        redisManager.setPassword(shiroSettings.getRedis().getPassword());
        redisManager.setPort(shiroSettings.getRedis().getPort());
        redisManager.setExpire(shiroSettings.getRedis().getExpire());
        return redisManager;
    }


}
