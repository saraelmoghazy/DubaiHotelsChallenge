
package com.hotels.tajawal.dubaihotels.hotels.model;

import com.google.gson.annotations.Expose;

import java.util.List;


public class HotelResponse {

    @Expose
    private List<Hotel> hotels;

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotel) {
        this.hotels = hotel;
    }

}
