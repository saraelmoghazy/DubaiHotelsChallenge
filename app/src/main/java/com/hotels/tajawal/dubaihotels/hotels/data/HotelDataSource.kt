//package com.hotels.tajawal.dubaihotels.hotels.data
//
//import com.hotels.tajawal.dubaihotels.base.BaseObservable
//import com.hotels.tajawal.dubaihotels.hotels.model.HotelResponse
//import io.reactivex.Observable
//import retrofit2.Retrofit
//import javax.inject.Inject
//
///**
// * Created by Sara Elmoghazy on 19,November,2018
// */
//class HotelDataSource @Inject constructor(
//        private val retrofit: Retrofit) : BaseObservable<HotelResponse>() {
//
//    fun getHotels(): Observable<HotelResponse> {
//        return retrofit.create(HotelService::class.java).getHotels()
//    }
//
//}