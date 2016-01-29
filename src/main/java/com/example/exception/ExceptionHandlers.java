package com.example.exception;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhui on 2016/1/22.
 * 异常处理类
 */
@ControllerAdvice
public class ExceptionHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler
    void handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException, ServletException {
        System.out.println(request.getRequestURL());
        LOGGER.error("", ex);
        PrintWriter pw = response.getWriter();
        HttpStatus status = getStatus(request);
        Map<Integer, Object> map = new HashMap<>();
        map.put(status.value(), "internal error");
        //输入验证
        if(ex instanceof BindException){
            BindException e = (BindException) ex;
            BindingResult result = e.getBindingResult();
            if(result.hasErrors()){
                map.put(status.value(),result.getAllErrors().get(0).getDefaultMessage());
                pw.write(JSON.toJSONString(map));
                pw.close();
            }
        }else if(ex instanceof BaseException){
            map.put(status.value(),ex.getMessage());
            pw.write(JSON.toJSONString(map));
            pw.close();
        }else{
            response.sendError(status.value());
        }

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
