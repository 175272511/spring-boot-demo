package com.example.demo.web;

import com.example.demo.domain.TAuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuhui on 2016/2/5.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("index")
    public String index(){
        return "login";
    }

    @RequestMapping("login")
    public String login(TAuthUser user, Model model){

//        Subject subject = SecurityUtils.getSubject();
//        if (!subject.isAuthenticated()) {
//            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//            token.setRememberMe(true);
//            subject.login(token);
//        }

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

    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
