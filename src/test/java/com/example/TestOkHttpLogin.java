package com.example;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by liuhui on 2017/8/10.
 */
public class TestOkHttpLogin {

    static OkHttpClient client = new OkHttpClient();
//    static String loginUrl = "http://mllmtest.com/cas/login?service=http://mllmtest.com/stats/shiro-cas";
    static String loginUrl = "http://mllmtest.com/cas/login?service=http://mllmtest.com/stats/shiro-cas";
    public static void main(String[] args) throws IOException {
//        Request request = new Request.Builder().url(loginUrl).get().build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()) {
//            System.out.println(response.body().string());
//        }

        Document document = Jsoup.connect(loginUrl).get();
        String lt = document.select("input[name=lt]").get(0).val();
        String execution = document.select("input[name=execution]").get(0).val();
        String _eventId = document.select("input[name=_eventId]").get(0).val();
        String locale = document.select("input[name=locale]").get(0).val();

        //登录
        FormBody formBody = new FormBody.Builder()
                .add("username", "")
                .add("password", "")
                .add("lt", lt)
                .add("execution", execution)
                .add("_eventId", _eventId)
                .add("locale", locale)
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(loginUrl)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
