package com.hotels.tajawal.dubaihotels.base;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Base Observables for all observables , centralize subscribeOn / observeOn threads
 * @author Sara Elmoghazy
 */
public class BaseObservable<M> {

    private final Scheduler subscribeOnThread;
    private final Scheduler postExecutionThread;

    public BaseObservable() {
        this.subscribeOnThread = Schedulers.newThread();
        this.postExecutionThread = AndroidSchedulers.mainThread();
    }

    public Observable<M> getObservable(Observable<M> observable) {
        return observable.subscribeOn(subscribeOnThread).observeOn(postExecutionThread);
    }
}
