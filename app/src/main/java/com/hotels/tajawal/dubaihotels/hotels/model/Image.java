
package com.hotels.tajawal.dubaihotels.hotels.model;

import com.google.gson.annotations.Expose;


public class Image {

    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
