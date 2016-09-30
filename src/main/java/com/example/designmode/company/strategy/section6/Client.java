package com.example.designmode.company.strategy.section6;

import java.util.Arrays;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class Client {
	
	public static void main(String[] args) {
		//输入的两个参数是数字
		int a = Integer.parseInt("10");
		String symbol = "+";  //符号
		int b = Integer.parseInt("20");
		System.out.println("输入的参数为："+Arrays.toString(args));
	
		System.out.println("运行结果为："+a + symbol + b + "=" + Calculator.ADD.exec(a, b));
		
	}
}
