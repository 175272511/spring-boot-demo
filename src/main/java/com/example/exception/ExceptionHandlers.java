package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, BindingResult result, Throwable ex) {
        LOGGER.error("Requested URL=", request.getRequestURL());
        LOGGER.error("Requested URI=", request.getRequestURI());
        LOGGER.error("", ex);
        HttpStatus status = getStatus(request);
        Map<Integer, Object> map = new HashMap<>();
        if(result.hasErrors()){
            map.put(status.value(),result.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(map, status);
        }
        map.put(status.value(), "internal error");
        return new ResponseEntity<>(map, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
