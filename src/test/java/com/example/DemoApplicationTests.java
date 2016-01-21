//package com.example;
//
//import com.example.config.ApplicationContextConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DemoApplication.class)
//@WebAppConfiguration
//public class DemoApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//	@Test
//	public void sayHelloWorld(){
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
//		System.out.println("======>" + ctx.getBean("message"));
//	}
//
//}
