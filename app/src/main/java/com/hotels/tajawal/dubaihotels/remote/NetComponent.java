package com.hotels.tajawal.dubaihotels.remote;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Sara Elmoghazy.
 */
@ForApplication
@Component(modules = {NetModule.class})
public interface NetComponent {
    String BaseUrl = "https://testapi.io/api/a-manumohan/";

    Retrofit getRetrofit();

    class Initializer {

        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {
                component = DaggerNetComponent.builder()
                        .netModule(new NetModule(BaseUrl))
                        .build();
            }

            return component;
        }
    }
}
