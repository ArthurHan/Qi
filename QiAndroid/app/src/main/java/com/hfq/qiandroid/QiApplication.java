package com.hfq.qiandroid;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class QiApplication extends Application {

    OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .build();
    OkHttpUtils utils = OkHttpUtils.initClient(client);
}
