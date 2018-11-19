package com.hotels.tajawal.dubaihotels.hotels.data;

import com.hotels.tajawal.dubaihotels.hotels.model.HotelResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author Sara Elmoghazy
 */
public interface HotelService {

    @GET("https://testapi.io/api/a-manumohan/hotels")
    Observable<HotelResponse> getHotels();

}
