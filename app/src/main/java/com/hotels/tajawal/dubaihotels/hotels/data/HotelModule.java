package com.hotels.tajawal.dubaihotels.hotels.data;

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
    HotelDataSource provideHotelDataSource(Retrofit retrofit) {
        return new HotelDataSource(retrofit);
    }
}
