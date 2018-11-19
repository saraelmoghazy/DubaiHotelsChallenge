package com.hotels.tajawal.dubaihotels.base;

import android.support.annotation.NonNull;

import com.hotels.tajawal.dubaihotels.remote.RetrofitException;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFetchObserver<M> implements Observer<M> {

    private final String TAG = this.getClass().getSimpleName();
    private WeakReference<BaseViewModel> baseViewModel;
    private CompositeDisposable disposables;
    private int id;

    /**
     * @param baseViewModel to handle call backs.
     * @param id            id of the useCase that use this observer.
     */
    public BaseFetchObserver(@NonNull BaseViewModel baseViewModel, int id) {
        this.baseViewModel = new WeakReference(baseViewModel);
        this.disposables = new CompositeDisposable();
        this.id = id;
    }

    @Override
    public void onError(Throwable exception) {
        if (exception instanceof RetrofitException) {
            baseViewModel.get().handleError((RetrofitException) exception, id);
        }
    }

    @Override
    public void onNext(@io.reactivex.annotations.NonNull M m) {
        baseViewModel.get().doOnNext();
    }

    @Override
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
        addDisposable(d);
    }

    @Override
    public void onComplete() {
        destroy();
    }

    /**
     * Dispose from current {@link CompositeDisposable} and destroy any other references if found
     */
    public void destroy() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     *
     * @param disposable
     */
    private void addDisposable(@NonNull Disposable disposable) {
        disposables.add(disposable);
    }

}