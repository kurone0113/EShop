package com.feicuiedu.eshop_20170518.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public Call getHomeBanner(){
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL + "/home/data")
                .build();
        return client.newCall(request);
    }

    public Call getHomeCategory(){
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL + "/home/category")
                .build();
        return client.newCall(request);
    }

    public Bitmap getBitmapFromAddress(String Url) {
        URL url = null;
        HttpURLConnection conn = null;
        byte[] imagebytes = null;
        try {
            url = new URL(Url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            //获取服务器返回回来的流
            InputStream is = conn.getInputStream();
            imagebytes = getBytes(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeByteArray(imagebytes, 0, imagebytes != null ? imagebytes.length : 0);
    }

    private byte[] getBytes(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            is.close();
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] result = bos.toByteArray();
        return result;
    }
}
