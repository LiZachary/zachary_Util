
package com.zachary.utli.Net.interceptor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2015/10/30.
 */
public class CurlInterceptor implements Interceptor {

    private static final String TAG = "Ok2Curl";

    private Logger logger = Logger.getLogger(TAG);

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();

        final Request copy = request.newBuilder().build();
        final String curl = new CurlBuilder(copy).build();
        logger.log(Level.INFO, curl);
        return chain.proceed(request);
    }
}