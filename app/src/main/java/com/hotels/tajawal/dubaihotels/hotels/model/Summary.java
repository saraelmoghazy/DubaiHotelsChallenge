
package com.hotels.tajawal.dubaihotels.hotels.model;

import com.google.gson.annotations.Expose;


public class Summary {

    @Expose
    private Double highRate;
    @Expose
    private String hotelName;
    @Expose
    private Double lowRate;

    public Double getHighRate() {
        return highRate;
    }

    public void setHighRate(Double highRate) {
        this.highRate = highRate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Double getLowRate() {
        return lowRate;
    }

    public void setLowRate(Double lowRate) {
        this.lowRate = lowRate;
    }

}
