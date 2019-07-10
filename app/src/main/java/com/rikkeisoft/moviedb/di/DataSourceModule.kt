package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.data.MovieDataSource
import com.rikkeisoft.moviedb.data.local.source.MovieLocalDataSource
import com.rikkeisoft.moviedb.data.remote.source.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteMovieDataSource: MovieRemoteDataSource): MovieDataSource.Remote

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(remoteMovieDataSource: MovieLocalDataSource): MovieDataSource.Local
}
