package com.graduation.design.bestellen.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pan on 2017/5/9.
 * base remote repository
 */
open class RemoteDataRepository {
//    val BASE_URL = "http://10.0.2.2:6789/"
    val BASE_URL = "http://www.jlulearn.cn:6789/"

    open val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

}