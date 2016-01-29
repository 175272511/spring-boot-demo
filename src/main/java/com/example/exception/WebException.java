package com.example.exception;

/**
 * Created by leo on 2016/1/29.
 * 用于返回页面
 */
public class WebException extends RuntimeException {

    public WebException() {
        super();
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }
}
