package com.hotels.tajawal.dubaihotels.remote;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by Sara Elmoghazy.
 */
@Singleton
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
