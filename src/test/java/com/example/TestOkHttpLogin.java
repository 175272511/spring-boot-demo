package com.example;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuhui on 2017/8/10.
 */
public class TestOkHttpLogin {

//    static OkHttpClient client = new OkHttpClient();
    static String loginUrl = "http://mllmtest.com/cas/login?service=http://mllmtest.com/cp/shiro-cas";
    public static void main(String[] args) throws IOException {
        final HashMap<String, List<okhttp3.Cookie>> cookieStore = new HashMap<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {

                    @Override
                    public void saveFromResponse(HttpUrl url, List<okhttp3.Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);

                    }

                    @Override
                    public List<okhttp3.Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<okhttp3.Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<okhttp3.Cookie>();
                    }
                })
                .build();


        Document document = Jsoup.connect(loginUrl).get();
        String lt = document.select("input[name=lt]").get(0).val();
        String execution = document.select("input[name=execution]").get(0).val();
        String _eventId = document.select("input[name=_eventId]").get(0).val();
        String locale = document.select("input[name=locale]").get(0).val();

        //登录
        FormBody formBody = new FormBody.Builder()
                .add("username", "xiao")
                .add("password", "123456")
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
        String cookieVal = cookieStore.get("mllmtest.com").get(0).toString();
        String cookieStr = cookieVal.substring(0, cookieVal.indexOf(";")).split("=")[1];
        javax.servlet.http.Cookie cookie = new Cookie("JSESSIONID", cookieStr);
        cookie.setPath("/cas/");
        cookie.setHttpOnly(true);

        String url = "http://mllmtest.com/stats//home/overview/index?menuKey=stats:sjgl";
        request = new Request.Builder().url(url).get().build();
        response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        }
    }
}
