package com.example.exception;

/**
 * Created by liuhui on 2016/1/22.
 * 异常类
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 7161144557877196634L;

	/**
	 * 异常码
	 */
	private int code;

	public BaseException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BaseException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
