package com.example;


//import jdk.incubator.http.HttpClient;
//import jdk.incubator.http.HttpRequest;
//import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by liuhui on 2017/9/22.
 */
public class TestJava9 {
    public static void main(String[] args) {
//        java8
//        一、Lambda表达式
        Arrays.asList("a","b","c").forEach(e -> System.out.println(e));
//        二、接口的默认方法与静态方法
//        三、方法引用
//        四、重复注解
//        五、扩展注解的支持 几乎可以为任何东西添加注解，包括局部变量、泛型类、父类与接口的实现，连方法的异常也能添加注解。
//        六、Optional
//        八、Date/Time API (JSR 310)
//        九、JavaScript引擎Nashorn
//        十、Base64
//        4.2 Stream

//        java9
//        Set<Integer> ints = Set.of(1, 2, 3);
//        List<String> strings = List.of("first", "second");
//        IntStream.iterate(1, i -> i < 100, i -> i + 1).forEach(System.out::println);
//        Stream<Integer> s = Optional.of(1).stream();
//        MyInterface myInterface = new MyInterfaceImpl();
//        myInterface.interfaceMethodWithDefault();
//        myInterface.normalInterfaceMethod();
//        myInterface.anotherDefaultMethod();
//        myInterface.init();

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest req = HttpRequest.newBuilder(URI.create("http://www.google.com"))
//                        .header("User-Agent","Java")
//                        .GET()
//                        .build();
//        try {
//            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandler.asString());
//            System.out.println(resp.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    interface MyInterface{
        void normalInterfaceMethod();

        default void interfaceMethodWithDefault() {  init(); }

        default void anotherDefaultMethod() { init(); }

        // This method is not part of the public API exposed by MyInterface
        private void init() { System.out.println("Initializing"); }
    }

    static class MyInterfaceImpl implements MyInterface{

        @Override
        public void normalInterfaceMethod() {

        }

        private void init(){
            System.out.println("Initializing1111");
        }

    }
}
