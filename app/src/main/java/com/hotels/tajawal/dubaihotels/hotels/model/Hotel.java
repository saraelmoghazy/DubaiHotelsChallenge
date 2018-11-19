
package com.hotels.tajawal.dubaihotels.hotels.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Hotel {

    @Expose
    private Long hotelId;
    @Expose
    private List<Image> image;
    @Expose
    private Location location;
    @Expose
    private Summary summary;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

}
