package com.feicuiedu.eshop_20170518.network.core;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Kurone on 2017/5/22.
 */

public abstract class UICallBack implements Callback {
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailureInUi(call,e);
            }
        });
    }

    @Override
    public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onResponseInUi(call,response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public abstract void onFailureInUi(@NonNull Call call, @NonNull IOException e);

    public abstract void onResponseInUi(@NonNull Call call, @NonNull Response response) throws IOException;

}
