package com.hotels.tajawal.dubaihotels.remote;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sara Elmoghazy.
 * Interceptor to add common parameters to request.
 */
public class NetworkCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(60, TimeUnit.MINUTES)
                .build();

        response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
        return response;
    }
}
