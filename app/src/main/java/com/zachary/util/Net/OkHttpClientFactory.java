package com.zachary.util.Net;

import android.os.Environment;

import com.zachary.util.Net.interceptor.HttpLoggingInterceptor;
import com.zachary.util.Net.interceptor.RewriteCacheControlInterceptor;
import com.zachary.util.Net.interceptor.UserAgentInterceptor;
import com.zachary.util.Net.retry.DefaultRetryPolicy;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by zachary on 2018/10/29.
 */
public class OkHttpClientFactory {

    /**
     * Socket timeout in milliseconds for image requests
     */
    private static final int TIMEOUT_MS = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;

    /**
     * Singleton instance.
     */
    private static final OkHttpClient CLIENT;
    private static final OkHttpClient.Builder builder;

    public static Boolean DEBUG;
    public static String packageName;
    public static final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 10;// 10M
    private static File file;

    static {
        initDebugBuild();

        builder=new OkHttpClient.Builder();
        builder.connectTimeout(TIMEOUT_MS, TimeUnit.MILLISECONDS);
        final String cacheDir = "/Android/data/" + packageName + "/cache/";
        file = new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
        builder.cache(new Cache(file, DEFAULT_CACHE_SIZE));
        builder.networkInterceptors().add(new RewriteCacheControlInterceptor());

        // 不可以在网络层拦截.
        //builder.interceptors().add(new RetryInterceptor());

        builder.interceptors().add(new UserAgentInterceptor());

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.interceptors().add(loggingInterceptor);
        CLIENT = builder.build();
    }

    /**
     * @return A usable OkHttpClient instance.
     */
    public static OkHttpClient getClient() {
        return CLIENT;
    }

    /**
     * still broken for library projects? If so, use this.</p>
     *
     * See: https://code.google.com/p/android/issues/detail?id=52962</p>
     *
     * @return {@code true} if this is a debug build, {@code false} if it is a production build.
     */
    public static void initDebugBuild() {
        if (DEBUG == null) {
            try {
                final Class<?> activityThread = Class.forName("android.app.ActivityThread");
                final Method currentPackage = activityThread.getMethod("currentPackageName");
                packageName = (String) currentPackage.invoke(null, (Object[]) null);
                final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
                final Field buildConfigField = buildConfig.getField("DEBUG");
                buildConfigField.setAccessible(true);
                DEBUG = buildConfigField.getBoolean(null);
            } catch (final Throwable t) {
                final String message = t.getMessage();
                packageName = "com.zachary.util";
                if (message != null && message.contains("BuildConfig")) {
                    // Proguard obfuscated build. Most likely a production build.
                    DEBUG = false;
                } else {
//                    DEBUG = BuildConfig.DEBUG;
                }
            }
        }
    }
}
