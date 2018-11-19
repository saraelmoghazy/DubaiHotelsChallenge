package com.hotels.tajawal.dubaihotels;

import android.app.Application;

/**
 * Created by Sara Elmoghazy.
 */

public class TajawalApp extends Application {

    private static TajawalApp context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Application getInstance() {
        return context;
    }
}
