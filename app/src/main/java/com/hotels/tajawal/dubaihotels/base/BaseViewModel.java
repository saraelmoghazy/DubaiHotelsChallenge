package com.hotels.tajawal.dubaihotels.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.hotels.tajawal.dubaihotels.remote.RetrofitException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base view model centralize execute failed use cases , error handling.
 * Created by Sara Elmoghazy.
 */
public class BaseViewModel extends ViewModel {
    public static final String TAG = BaseViewModel.class.getSimpleName();

    private Map<Integer, BaseUseCase> useCases;
    private List<BaseUseCase> failedUseCasesList = new ArrayList<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<RetrofitException> hasError = new MutableLiveData<>();

    public BaseViewModel() {
        isLoading.setValue(false);
    }

    public void start() {
        fillAllUseCasesList();
    }

    /**
     * Read all {@link #useCases} annotated with useCase annotation and add them to local list
     */
    public void fillAllUseCasesList() {
        try {
            useCases = retrieveDeclaredUSeCases();
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Read all useCases annotated with useCase annotation.
     */
    private Map<Integer, BaseUseCase> retrieveDeclaredUSeCases() throws IllegalAccessException {
        Map<Integer, BaseUseCase> useCases = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (isAnnotatedWithUseCaseAnnotation(field, UseCase.class)) {
                field.setAccessible(true);
                int id = readAnnotationValue(field, UseCase.class);
                useCases.put(id, (BaseUseCase) field.get(this));
            }
        }
        return useCases;
    }

    private boolean isAnnotatedWithUseCaseAnnotation(Field field, Class<UseCase> annotation) {
        return field.isAnnotationPresent(annotation);
    }

    private int readAnnotationValue(Field field, Class<UseCase> annotation) {
        return field.getAnnotation(annotation).value();
    }

    /**
     * Restart this view model so this will restart all failed useCases.
     */
    public void restart() {
        setIsLoading(true);
        executeAllFailedUseCases();
    }


    protected void executeAllFailedUseCases() {
        for (BaseUseCase useCase : failedUseCasesList) {
            useCase.reExecute();
        }
        failedUseCasesList.clear();
    }


    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public void handleError(RetrofitException error, int id) {
        setIsLoading(false);
        //add to fail list
        addUseCaseToFailedList(id);
        // expose error
        hasError.setValue(error);
    }

    public void doOnNext() {
        hasError.setValue(null);
    }


    /**
     * keeps reference of this useCase to reExecute it when view model restarts.
     *
     * @param id id of the failed useCase.
     */
    private void addUseCaseToFailedList(int id) {
        if (useCases != null && !useCases.isEmpty()) {
            BaseUseCase useCase = useCases.get(id);
            if (useCase != null) {
                failedUseCasesList.add(useCase);
            }
        }
    }

    public MutableLiveData<RetrofitException> getHasError() {
        return hasError;
    }

}
