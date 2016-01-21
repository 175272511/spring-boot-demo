package com.example.javaconfig.druidconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhui on 2016/1/19.
 * 配置Druid连接池
 */
@Configuration
@EnableConfigurationProperties(DruidSettings.class)
public class DruidConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfiguration.class);

    public DruidConfiguration(){
        LOGGER.debug("Registered Druid Servlet");
    }

    /**
     * 配置数据源
     * @param ds
     * @return
     * @throws SQLException
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource(DruidSettings ds) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(ds.getUrl());
        dataSource.setUsername(ds.getUsername());
        dataSource.setPassword(ds.getPassword());
        dataSource.setInitialSize(ds.getInitialSize());
        dataSource.setMaxActive(ds.getMaxActive());
        dataSource.setMinIdle(ds.getMinIdle());
        dataSource.setMaxWait(ds.getMaxWait());
        dataSource.setValidationQuery(ds.getValidationQuery());
        dataSource.setTimeBetweenEvictionRunsMillis(ds.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(ds.getMinEvictableIdleTimeMillis());
        dataSource.setTestWhileIdle(ds.isTestWhileIdle());
        dataSource.setTestOnBorrow(ds.isTestOnBorrow());
        dataSource.setTestOnReturn(ds.isTestOnReturn());
        dataSource.setPoolPreparedStatements(ds.isPoolPreparedStatements());
        dataSource.setMaxOpenPreparedStatements(ds.getMaxOpenPreparedStatements());

        try {
            dataSource.setFilters(ds.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("init druid source error", e);
        }

        LOGGER.debug("init druid source");
        return dataSource;

    }

    /**
     * 配置Druid过滤器
     * @param ds
     * @return
     */
    @Bean
    public FilterRegistrationBean druidWebStatFilter(DruidSettings ds){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());

        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions", ds.getExclusions());
        initParameters.put("sessionStatMaxCount", ds.getSessionStatMaxCount().toString());
        initParameters.put("sessionStatEnable", ds.isSessionStatEnable() + "");
        if(ds.getPrincipalSessionName()!=null){
            initParameters.put("principalSessionName", ds.getPrincipalSessionName());
        }
        initParameters.put("profileEnable", ds.isProfileEnable() + "");
        registrationBean.setInitParameters(initParameters);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    /**
     * 配置Druid Servlet支持监控页面
     * @param ds
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatView(DruidSettings ds){

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("resetEnable", ds.isResetEnable() + "");
        initParameters.put("loginUsername", ds.getLoginUsername());
        initParameters.put("loginPassword", ds.getLoginPassword());
        registrationBean.setInitParameters(initParameters);
        return registrationBean;
    }
}
