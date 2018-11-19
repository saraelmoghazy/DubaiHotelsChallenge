package com.hotels.tajawal.dubaihotels.hotels.data;

import com.hotels.tajawal.dubaihotels.hotels.GetHotelsUseCase;
import com.hotels.tajawal.dubaihotels.remote.NetComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sara Elmoghazy on 09,September,2018
 */
@Singleton
@Component(dependencies = {NetComponent.class}, modules = {HotelModule.class})
public interface HotelDataSourceComponent {

    void inject(GetHotelsUseCase getHotelsUseCase);

    public class Initializer {

        private static HotelDataSourceComponent component;

        public static HotelDataSourceComponent buildComponent() {
            if (component == null) {
                component = DaggerHotelDataSourceComponent.builder()
                        .netComponent(NetComponent.Initializer.buildComponent())
                        .hotelModule(new HotelModule()).build();
            }
            return component;
        }
    }

}
