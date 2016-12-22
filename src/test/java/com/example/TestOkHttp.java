package com.example;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by liuhui on 2016/12/5.
 */
public class TestOkHttp {
    static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            throw new IOException("Unexpected code " + response);
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("JSON"), "json");
        Request request1 = new Request.Builder().url("").post(requestBody).build();
        Response response1 = client.newCall(request1).execute();
        response1.body().string();

        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
