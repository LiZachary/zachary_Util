/*
 * Copyright (C) 2015 House365. All rights reserved.
 */

package com.zachary.util.Net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2015/10/30.
 */
public class TokenInterceptor implements Interceptor {

    public TokenInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // TODO 添加Token处理逻辑
//        String secret = tokenHelper.getSecret();
//
//        if (!TextUtils.isEmpty(secret)) {
//            request = request.newBuilder()
//                    .addHeader("Authorization", "Bearer " + secret)
//                    .build();
//        }

        return chain.proceed(request);
    }
}