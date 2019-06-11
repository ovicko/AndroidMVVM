/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private Retrofit.Builder retrofit;

    public RetrofitHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient()
                        .newBuilder()
                        .addNetworkInterceptor(
                                new HttpLoggingInterceptor()
                                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build());

    }

    public <T> T getService(Class<T> tService) {
        return retrofit.build().create(tService);
    }

    public static <T> void request(Response<T> tResponse, ApiListener<T> listener) {
        if (tResponse.isSuccessful()) {
            listener.onSuccess(tResponse.body());
        } else {
            listener.onServerError(tResponse.errorBody().toString());
        }

    }

    public interface ApiListener<T> {

        void onSuccess(T t);

        void onError(Throwable throwable);

        void onServerError(String errorMessage);
    }
}
