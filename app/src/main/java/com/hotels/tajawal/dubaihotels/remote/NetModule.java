package com.hotels.tajawal.dubaihotels.remote;

import com.hotels.tajawal.dubaihotels.TajawalApp;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Sara Elmoghazy
 */
@Module
@ForApplication
public class NetModule {

    String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    public OkHttpClient provideOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // cache network request for 1 min because of late response of API's
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(TajawalApp.getInstance().getCacheDir(), cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .addInterceptor(new NetworkCacheInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build();

        return okHttpClient;
    }

    @Provides
    public Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    public RxErrorHandlingCallAdapterFactory provideRxErrorHandlerCallAdapter(Retrofit retrofit) {
        return new RxErrorHandlingCallAdapterFactory(retrofit);
    }

    @Provides
    public RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory observableFactory,
                                            Retrofit retrofit) {
        return new RetrofitProvider(observableFactory, retrofit);
    }
}
