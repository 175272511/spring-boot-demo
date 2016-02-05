package com.example.javaconfig.shiroconfig;

import com.example.javaconfig.druidconfig.DruidConfiguration;
import com.example.javaconfig.mybatisconfig.MybatisConfiguration;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public EnterpriseCacheSessionDAO sessionDAO(){
        //TODO 后续采用redis管理会话
        return  new EnterpriseCacheSessionDAO();
    }

    @Bean
    public SimpleCookie sessionIdCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(180000);
        return simpleCookie;
    }

    /**
     * 会话验证调度器,用于在web环境中定时检测会话是否过期并停止会话
     * @return
     */
//    @Bean
//    public QuartzSessionValidationScheduler sessionValidationScheduler(DefaultWebSessionManager sessionManager){
//        return new QuartzSessionValidationScheduler(sessionManager);
//    }

    /**
     * 配置集中式会话管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager(SessionDAO sessionDAO, SimpleCookie simpleCookie){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //会话的全局过期时间
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        //是否删除过期的会话
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        //是否开启会话验证调度器,默认开启
//        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //设置会话验证调度器
//        defaultWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler);
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
    public DefaultWebSecurityManager securityManager(Collection<Realm> realms, SessionManager sessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealms(realms);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        //TODO 后续增加缓存管理器
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


}
