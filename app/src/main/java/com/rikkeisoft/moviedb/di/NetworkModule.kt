package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.service.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi() = NetworkService().createAppRetrofit()
}
