package com.hotels.tajawal.dubaihotels.hotels;

import com.hotels.tajawal.dubaihotels.base.BaseUseCase;
import com.hotels.tajawal.dubaihotels.hotels.data.HotelDataSource;
import com.hotels.tajawal.dubaihotels.hotels.data.HotelDataSourceComponent;
import com.hotels.tajawal.dubaihotels.hotels.model.HotelResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Sara Elmoghazy.
 */
public class GetHotelsUseCase extends BaseUseCase<HotelResponse> {

    @Inject
    HotelDataSource hotelDataSource;

    public GetHotelsUseCase() {
        HotelDataSourceComponent.Initializer
                .buildComponent().inject(this);
    }

    @Override
    public Observable<HotelResponse> buildUseCaseObservable() {
        return hotelDataSource.getHotels();
    }
}
