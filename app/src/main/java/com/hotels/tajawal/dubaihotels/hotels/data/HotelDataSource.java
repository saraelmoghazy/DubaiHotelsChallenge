package com.hotels.tajawal.dubaihotels.hotels.data;

import com.hotels.tajawal.dubaihotels.base.BaseObservable;
import com.hotels.tajawal.dubaihotels.hotels.model.HotelResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Sara Elmoghazy on 06,September,2018
 */
public class HotelDataSource extends BaseObservable {

    Retrofit retrofit;
    HotelService categoriesAPI;


    public HotelDataSource(Retrofit retrofitProvider) {
        this.retrofit = retrofitProvider;
        categoriesAPI = retrofit.create(HotelService.class);
    }


    public Observable<HotelResponse> getHotels() {
        return getObservable(categoriesAPI.getHotels());

    }
}
