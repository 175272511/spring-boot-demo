package com.example.web;

import com.example.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuhui on 2016/1/18.
 */
@Controller
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private CityMapper cityMapper;

    @RequestMapping("/")
    public String home(){
        LOGGER.debug("===>index init");
        return "welcome";
    }

    @RequestMapping("/findbystate")
    @ResponseBody
    public String findByState(){
        return cityMapper.findByState();
    }
}
