/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.ovicko.viewmodelandroid.model.ApiMessage;
import com.ovicko.viewmodelandroid.model.Hit;
import com.ovicko.viewmodelandroid.model.Pixabay;
import com.ovicko.viewmodelandroid.network.RetrofitApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixabayRepository extends BaseRepository {
    RetrofitApi retrofitApi;
    private MutableLiveData<List<Hit>> imageHitListLiveData;
    private  MutableLiveData<ApiMessage> errorLiveData;

    public PixabayRepository(Context context) {
        this.imageHitListLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();

        retrofitApi = getRetrofitHelper().getService(RetrofitApi.class);
    }

    public LiveData<List<Hit>> loadImagesFromApi() {
        Call<Pixabay> pixabayCall;
        pixabayCall = retrofitApi.getImageList("6467369-0b944d36565eca4ea15902559");

        pixabayCall.enqueue(new Callback<Pixabay>() {
            @Override
            public void onResponse(Call<Pixabay> call, Response<Pixabay> response) {
                Pixabay pixabay =  response.body();

                if (response.isSuccessful()) {
                    imageHitListLiveData.setValue(pixabay.getHits());
                    errorLiveData.setValue(new ApiMessage("Successful transaction_ without change"));
                } else {
                    errorLiveData.setValue(new ApiMessage("Something bad happened without change!"));
                }
            }

            @Override
            public void onFailure(Call<Pixabay> call, Throwable t) {

                errorLiveData.setValue(new ApiMessage(t.getMessage()));
            }
        });

        return  imageHitListLiveData;

    }

    public LiveData<ApiMessage> getErrorMutableLiveData() {
        return errorLiveData;
    }


}
