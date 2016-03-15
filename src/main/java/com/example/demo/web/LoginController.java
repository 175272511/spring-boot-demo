package com.example.demo.web;

import com.example.demo.domain.TAuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by liuhui on 2016/2/5.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("index")
    public String index(){
        //TODO 判断session是否保存在redis
        Session session = SecurityUtils.getSubject().getSession();
        return "login";
    }

    @RequestMapping("login")
    public String login(TAuthUser user, Model model){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
        }catch (Exception e){
            LOGGER.warn("帐号密码错误");
            return "redirect:index";
        }

        return "welcome";
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("welcome")
    @RequiresPermissions("admin:test1")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
