/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid.network;

import com.ovicko.viewmodelandroid.model.Pixabay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
    String BASE_URL = "https://pixabay.com/";

    @GET("api/")
    Call<Pixabay> getImageList(@Query("key") String apiKey);
}
