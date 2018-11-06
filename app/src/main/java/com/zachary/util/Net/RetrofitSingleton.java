package com.zachary.util.Net;

import android.content.Context;

import com.zachary.util.Net.converter.UrlEncodedConverterFactory;
import com.zachary.util.Net.interceptor.UserAgentInterceptor;
import com.zachary.util.Net.url.Links;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类描述：返回Retrofit的单一实例
 * 创建人：Zachary
 * 创建时间：2018/07/03 16:34
 */
public class RetrofitSingleton {

    public static Retrofit sInstanse;
    private static Retrofit.Builder builder;
    private static OkHttpClient okHttpClient;
    public static Retrofit getInstanse(Context context) {
        if (null == sInstanse) {
            sInstanse = initSyn(context);
        }
        return sInstanse;
    }

    private static synchronized Retrofit initSyn(Context context) {
        //设置userAgent&header
//        OkHttpClientFactory.setDefaultUserAgent(context.getApplicationContext());

        OkHttpClient.Builder builder = OkHttpClientFactory.getClient().newBuilder();

        //重新设置请求头信息
        builder.interceptors().add(new UserAgentInterceptor());
        okHttpClient = builder.build();

        return new Retrofit.Builder().client(okHttpClient)
                .baseUrl(Links.getHttpUrl())
                .addConverterFactory(UrlEncodedConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 获取Retrofit.Builder实例
     * @return
     */
    public static synchronized Retrofit.Builder getRetrofitBuilder(){
        if (builder == null) {
            builder = new Retrofit.Builder().client(okHttpClient)
                    .addConverterFactory(UrlEncodedConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
        }
        return builder;
    }
}
