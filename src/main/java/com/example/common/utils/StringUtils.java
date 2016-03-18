package com.example.common.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	public static boolean isNull(String str) {
		return null == str || str.trim().isEmpty();
	}

	public static boolean isNotBlank(String str) {
		return !isNull(str);
	}

	/**
	 * 转换为字节数组
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
	}
}
