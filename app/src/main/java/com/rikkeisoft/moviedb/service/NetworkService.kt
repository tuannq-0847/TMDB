package com.rikkeisoft.moviedb.service

import com.rikkeisoft.moviedb.data.remote.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    fun createAppRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
