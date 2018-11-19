package com.hotels.tajawal.dubaihotels.hotels.data;

import com.hotels.tajawal.dubaihotels.base.BaseObservable;
import com.hotels.tajawal.dubaihotels.hotels.model.HotelResponse;
import com.hotels.tajawal.dubaihotels.remote.RetrofitProvider;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Sara Elmoghazy on 06,September,2018
 */
public class HotelDataSource extends BaseObservable<HotelResponse> {

    RetrofitProvider retrofitProvider;
    HotelService hotelService;


    public HotelDataSource(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
        hotelService = retrofitProvider.getRetrofit().create(HotelService.class);
    }


    public Observable<HotelResponse> getHotels() {
        return getObservable(hotelService.getHotels());

    }
}
