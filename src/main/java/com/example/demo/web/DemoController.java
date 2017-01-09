package com.example.demo.web;

import com.example.demo.domain.TAuthUser;
import com.example.demo.service.TAuthUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.jlings.cache.annotation.JlingsCache;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by liuhui on 2016/1/18.
 */
@Controller
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private TAuthUserService tAuthUserService;

    @RequestMapping("/")
    public String home(@Valid TAuthUser user){
        LOGGER.debug(user.toString());
        return "welcome";
    }


    /**
     * 数据分页查询
     * @return
     */
    @RequestMapping("/getuserdata")
    @ResponseBody
    public PageInfo getUserData(){
        PageHelper.startPage(0, 10);
        List<TAuthUser> tAuthUsers = tAuthUserService.getUserInfo();
        PageInfo pageInfo = new PageInfo(tAuthUsers);
        return pageInfo;
    }

    @RequestMapping("/test")
    public String test() throws Exception {
        return "welcome";
    }
}
