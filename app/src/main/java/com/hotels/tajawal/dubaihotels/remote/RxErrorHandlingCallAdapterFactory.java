package com.hotels.tajawal.dubaihotels.remote;

import com.hotels.tajawal.dubaihotels.R;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Sara Elmoghazy
 */
public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;

    Retrofit retrofit;

    public RxErrorHandlingCallAdapterFactory(Retrofit retrofit) {
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
        this.retrofit = retrofit;
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxErrorHandlingCallAdapter(rxJava2CallAdapterFactory.get(returnType, annotations, retrofit));
    }

    private class RxErrorHandlingCallAdapter implements CallAdapter<R, Observable<R>> {
        private CallAdapter mWrapCallAdapter;

        public RxErrorHandlingCallAdapter(CallAdapter wrapped) {
            mWrapCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrapCallAdapter.responseType();
        }

        @Override
        public Observable<R> adapt(Call call) {
            return ((Observable<R>) mWrapCallAdapter.adapt(call)).onErrorResumeNext(throwable ->
            {
                return Observable.error(asRetrofitException(throwable));
            });
        }

        private RetrofitException asRetrofitException(final Throwable throwable) {
            RetrofitException retrofitException;
            if (throwable instanceof HttpException) {
                //TODO : parse errors bases on error messages.
                retrofitException = getErrorMessage(throwable);
            } else if (throwable instanceof IOException) {
                retrofitException = new RetrofitException(-1, throwable.getMessage(), ErrorType.NETWORK);
            } else {
                retrofitException = new RetrofitException(-1, throwable.getMessage(), ErrorType.UNEXPECTED);
            }
            return retrofitException;
        }

        /**
         * Get error message based on error response
         **/
        private RetrofitException getErrorMessage(Throwable throwable) {
            HttpException httpException = (HttpException) throwable;
            try {
                JSONObject jsonObject = new JSONObject(httpException.response().errorBody().string());
                return new RetrofitException(jsonObject.getInt("code"),
                        jsonObject.getString("message"), ErrorType.HTTP);
            } catch (Exception e) {
                return new RetrofitException(-1, throwable.getMessage(), ErrorType.UNEXPECTED);
            }
        }
    }
}