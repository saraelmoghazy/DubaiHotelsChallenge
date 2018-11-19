package com.hotels.tajawal.dubaihotels.hotels.data;

import com.hotels.tajawal.dubaihotels.remote.RetrofitProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Sara Elmoghazy on 06,September,2018
 */
@Module
public class HotelModule {

    @Provides
    @Singleton
    HotelDataSource provideHotelDataSource(RetrofitProvider retrofitProvider) {
        return new HotelDataSource(retrofitProvider);
    }
}
