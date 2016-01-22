package com.example.domain;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by liuhui on 2016/1/22.
 */
public class User {

    @NotNull(message = "用户名不能为空")
    private String username;

    private String password;

    @Email(message = "邮箱格式错误")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
