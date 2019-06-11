/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ovicko.viewmodelandroid.model.ApiMessage;
import com.ovicko.viewmodelandroid.model.Hit;
import com.ovicko.viewmodelandroid.repository.PixabayRepository;

import java.util.List;

public class ImageViewModel extends AndroidViewModel {

    private  LiveData<List<Hit>> imageHitListLiveData;
    private  LiveData<ApiMessage> apiMessageLiveData;
    private  PixabayRepository pixabayRepository;

    public ImageViewModel(@NonNull Application application) {
        super(application);
        pixabayRepository = new PixabayRepository(application.getApplicationContext());
        this.setPixaImages();
        setErrors();
    }

    public LiveData<List<Hit>> getPixaImages() {
        return  imageHitListLiveData;
    }

    private void setPixaImages() {
        imageHitListLiveData =  pixabayRepository.loadImagesFromApi();
    }

    public void setErrors() {
        apiMessageLiveData =  pixabayRepository.getErrorMutableLiveData();
    }

    public LiveData<ApiMessage> getError(){
        return apiMessageLiveData;
    }
}
