package com.feicuiedu.eshop_20170518.network;

import com.feicuiedu.eshop_20170518.network.entity.CategoryRsp;
import com.feicuiedu.eshop_20170518.network.entity.HomeBannerRsp;
import com.feicuiedu.eshop_20170518.network.entity.HomeCategoryRsp;
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

    @Test
    public void getHomeCategory() throws Exception {
        Call category = EshopClient.getInstance().getHomeCategory();
        Response response = category.execute();
        String json = response.body().string();
        HomeCategoryRsp homeCategoryRsp = new Gson().fromJson(json, HomeCategoryRsp.class);
        Assert.assertTrue(homeCategoryRsp.getStatus().isSucceed());
    }

    @Test
    public void getHomeBanner() throws Exception {
        Call category = EshopClient.getInstance().getHomeBanner();
        Response response = category.execute();
        String json = response.body().string();
        HomeBannerRsp homeBannerRsp = new Gson().fromJson(json, HomeBannerRsp.class);
        Assert.assertTrue(homeBannerRsp.getStatus().isSucceed());
    }

}