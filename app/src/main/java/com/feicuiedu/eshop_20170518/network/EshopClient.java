package com.feicuiedu.eshop_20170518.network;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Kurone on 2017/5/22.
 */

public class EshopClient {
    private static final String BASE_URL = "http://106.14.32.204/eshop/emobile/?url=";
    private static EshopClient eshopClient;
    private final OkHttpClient client;

    private EshopClient(){
        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    synchronized public static EshopClient getInstance(){
        if (eshopClient == null){
            eshopClient = new EshopClient();
        }
        return eshopClient;
    }
    public Call getCategory(){
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL+"/category")
                .build();
        return client.newCall(request);
    }
}
