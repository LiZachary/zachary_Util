/*
 * Copyright (C) 2015 House365. All rights reserved.
 */

package com.zachary.utli.Net.interceptor;

import com.zachary.utli.Net.util.HeadUtils;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.Version;

/**
 * Created by Administrator on 2015/11/2.
 */
public class UserAgentInterceptor implements Interceptor {
    private static final String TAG = "UserAgentInterceptor";
    private static final boolean DEBUG = true;

    private static String userAgent;
    private static String header;

    protected Logger logger = Logger.getLogger(TAG);

    public static String getHeader() {
        return header;
    }

    public static void setHeader(String header) {
        UserAgentInterceptor.header = header;
    }

    public UserAgentInterceptor() {
        userAgent = defaultUserAgent();
    }

    public UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (DEBUG)
            logger.config("intercept() called with: userAgent = [" + userAgent + "]");
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .header("X-FiberHome-Client", this.header)
                .build();
        return chain.proceed(requestWithUserAgent);
    }

    public static void setUserAgent(String userAgent) {
        UserAgentInterceptor.userAgent = userAgent;
    }

    public static void setXheader(String xheader) {
        setHeader(xheader);
    }

    private String defaultUserAgent() {
        String agent = System.getProperty("http.agent");
        header = HeadUtils.getHeader();
        if (DEBUG)
            logger.config("defaultUserAgent() called with: " + System.getProperty("http.agent"));
        return agent != null ? Util.toHumanReadableAscii(agent) : Version.userAgent();
    }
}
