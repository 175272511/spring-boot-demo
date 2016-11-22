package com.example.javaconfig.shiroconfig;

import java.io.Serializable;

/**
 * Created by liuhui on 2016/2/5.
 */
public class RedisSettings implements Serializable {

    private String host = "localhost";
    private String password;
    private int port = 6379;
    //无效, crazycake.RedisManager没有该配置
    private int database = 0;
    //放入redis中的用户、权限等信息超时时间，默认1800秒即半个小时，建议跟tomcat的session失效时间一致
    private int expire = 1800;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
