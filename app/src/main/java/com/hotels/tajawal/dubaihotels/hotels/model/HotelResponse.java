
package com.hotels.tajawal.dubaihotels.hotels.model;

import com.google.gson.annotations.Expose;

import java.util.List;


public class HotelResponse {

    @Expose
    private List<Hotel> hotel;

    public List<Hotel> getHotel() {
        return hotel;
    }

    public void setHotel(List<Hotel> hotel) {
        this.hotel = hotel;
    }

}
