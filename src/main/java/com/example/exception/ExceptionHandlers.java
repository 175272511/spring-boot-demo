package com.example.exception;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    void handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException {
        LOGGER.error("exception url: " + request.getRequestURL().toString());
        LOGGER.error("exception data: " + getParameter(request));
        LOGGER.error("exception info: ", ex);
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        Map<String, Object> map = new HashMap<>();

        //输入验证
        if(ex instanceof BindException){
            BindException e = (BindException) ex;
            BindingResult result = e.getBindingResult();
            if(result.hasErrors()){
                String errMsg = result.getAllErrors().get(0).getDefaultMessage();
                map.put("error", errMsg);
                if(errMsg.toUpperCase().contains("EXCEPTION")){
                    map.put("error", "请求失败,请检查输入数据是否有误");
                }
                pw.write(JSON.toJSONString(map));
                pw.close();
            }
        }else if(ex instanceof BaseException){
            map.put("error",ex.getMessage());
            pw.write(JSON.toJSONString(map));
            pw.close();
        }else{
            HttpStatus status = getStatus(request);
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

    private String getParameter(HttpServletRequest request){
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        queryString = queryString.substring(0, queryString.length() - 1);
        return queryString;
    }
}
