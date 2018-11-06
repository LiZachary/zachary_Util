/*
 * Copyright (C) 2015 House365. All rights reserved.
 */

package com.zachary.util.Net.interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp拦截器,用于重写响应头中的Cache-Control字段.
 * <p/>
 * Created by Administrator on 2015/10/29.
 */
public class RewriteCacheControlInterceptor implements Interceptor {

    private static final String TAG = "RewriteCacheControlInterceptor";
    private static final boolean DEBUG = true;

    public static int MAX_AGE = 5; //缓存时间,单位:分钟

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Response originalResponse = chain.proceed(chain.request());
        // TODO 加入强制缓存规则,用于兼容现有未按照规则编写的缓存控制的接口.

        // 加入默认缓存时间.
        if (originalResponse.cacheControl() == null) {
            CacheControl cacheControl = new CacheControl.Builder().maxAge(MAX_AGE, TimeUnit.MINUTES).build();
            if (originalRequest.cacheControl() != null) {
                cacheControl = originalRequest.cacheControl();
            }
            Response.Builder builder = originalResponse.newBuilder();
            // 不考虑http 1.0的"Pragma",去除干扰.
            if (originalResponse.header("Pragma") != null) {
                builder.removeHeader("Pragma");
            }
            builder.header("Cache-Control", cacheControl.toString());
            originalResponse = builder.build();
        }

        return originalResponse;
    }
}