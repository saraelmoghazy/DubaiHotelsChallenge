package com.hotels.tajawal.dubaihotels.base;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>
 * Abstract class for use case
 * each UseCase implementation will return the result using a {@link BaseFetchObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 * <p>
 */
public abstract class BaseUseCase<M> {

    private final Scheduler subscribeOnThread;
    private final Scheduler postExecutionThread;
    private BaseFetchObserver<M> baseFetchObserver;

    public BaseUseCase() {
        this.subscribeOnThread = Schedulers.newThread();
        this.postExecutionThread = AndroidSchedulers.mainThread();
    }
    /**
     * Builds an {@link Observable} which will be used when executing
     * the current {@link BaseUseCase}.
     * </P>
     */
    public abstract Observable<M> buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param observer {@link BaseFetchObserver <M>} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable()} ()} method.
     */
    public Observable<M> execute(@NonNull BaseFetchObserver<M> observer) {
        baseFetchObserver = observer;
        final Observable<M> observable = returnObservable();
        observable.subscribe(observer);

        return observable;
    }

    public Observable<M> reExecute() {
        final Observable<M> observable = returnObservable();
        observable.subscribeWith(baseFetchObserver);

        return observable;
    }

    private Observable<M> returnObservable() {
        return this.buildUseCaseObservable()
                .subscribeOn(subscribeOnThread)
                .observeOn(postExecutionThread);
    }

    /**
     * Dispose from current and destroy any other references if found
     */
    public void destroy() {
        if (baseFetchObserver != null) {
            baseFetchObserver.destroy();
            baseFetchObserver = null;
        }
    }
}