/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid.repository;

import com.ovicko.viewmodelandroid.network.RetrofitHelper;

public class BaseRepository {

//    Another thing I would like you to help i
//    s the Dagger, most tutorials have combined Rx and I end up mixing everything

    RetrofitHelper getRetrofitHelper(){
        return  new RetrofitHelper();
    }
}
