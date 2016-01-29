package com.example.web;

import com.example.domain.User;
import com.example.exception.BaseException;
import com.example.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by liuhui on 2016/1/18.
 */
@Controller
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CityMapper cityMapper;

    @RequestMapping("/")
    public String home(@Valid User user){
        LOGGER.debug("===>index init");
        return "welcome";
    }

    @RequestMapping("/findbystate")
    @ResponseBody
    public String findByState(){
        return cityMapper.findByState();
    }

    @RequestMapping("/test")
    public String test() throws Exception {
        throw new BaseException("eeeeeeeeeee");
//        return "welcome";
    }
}
