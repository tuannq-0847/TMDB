package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.service.NetworkService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideApi() = NetworkService().createAppRetrofit()
}
