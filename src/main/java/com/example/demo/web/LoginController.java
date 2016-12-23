package com.example.demo.web;

import com.example.demo.domain.TAuthUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;


/**
 * Created by liuhui on 2016/2/5.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String login(TAuthUser user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
        }catch (Exception e){
            LOGGER.warn("帐号密码错误");
            return "redirect:index";
        }
        subject.getSession().setAttribute("userInfo", user);
        SavedRequest savedRequest = (SavedRequest) subject.getSession().getAttribute(WebUtils.SAVED_REQUEST_KEY);
        System.out.println(savedRequest.getRequestURI());
        String redirect = "redirect:" + savedRequest.getRequestURI();
        return redirect;
    }

    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "index";
    }

    /**
     * admin的test1资源, user的test1资源可用
     * @param model
     * @return
     */
    @RequestMapping("welcome")
    @RequiresPermissions(value = {"admin:test1"},logical= Logical.OR)
    public String welcome(Model model){
        TAuthUser user = (TAuthUser) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        model.addAttribute("user", user);
        return "welcome";
    }

    @RequestMapping("welcome1")
    public String welcome1(){
        return "welcome1";
    }

    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
