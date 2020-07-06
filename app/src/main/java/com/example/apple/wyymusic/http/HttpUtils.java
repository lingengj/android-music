package com.example.apple.wyymusic.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ASUS on 2019/6/23.
 */

public class HttpUtils {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}