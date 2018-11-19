
package com.hotels.tajawal.dubaihotels.hotels.model;

import com.google.gson.annotations.Expose;


public class Location {

    @Expose
    private String address;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
