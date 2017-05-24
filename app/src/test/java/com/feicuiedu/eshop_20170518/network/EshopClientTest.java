package com.feicuiedu.eshop_20170518.network;

import com.feicuiedu.eshop_20170518.network.entity.CategoryRsp;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Kurone on 2017/5/22.
 */
public class EshopClientTest {
    @Test
    public void getCategory() throws Exception {
        Call category = EshopClient.getInstance().getCategory();
        Response response = category.execute();
        String json = response.body().string();
        CategoryRsp categoryRsp = new Gson().fromJson(json, CategoryRsp.class);
        Assert.assertTrue(categoryRsp.getStatus().isSucceed());
    }

}