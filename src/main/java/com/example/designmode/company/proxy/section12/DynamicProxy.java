package com.example.designmode.company.proxy.section12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class DynamicProxy<T> {

	public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h){
		T newProxyInstance = (T)Proxy.newProxyInstance(loader,interfaces, h);
		return newProxyInstance;
	}

	public static <T> T newProxyInstance(T t){
		ClassLoader classLoader = t.getClass().getClassLoader();
		Class<?>[] interfaces = t.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader,interfaces, new MyIvocationHandler(t));
	}
}
