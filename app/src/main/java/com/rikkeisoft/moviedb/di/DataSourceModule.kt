package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.data.DetailMovieDataSource
import com.rikkeisoft.moviedb.data.FavoriteDataSource
import com.rikkeisoft.moviedb.data.MovieDataSource
import com.rikkeisoft.moviedb.data.local.source.DetailLocalDataSource
import com.rikkeisoft.moviedb.data.local.source.FavoriteLocalDataSource
import com.rikkeisoft.moviedb.data.local.source.MovieLocalDataSource
import com.rikkeisoft.moviedb.data.remote.source.DetailMovieRemoteDataSource
import com.rikkeisoft.moviedb.data.remote.source.FavoriteRemoteDataSource
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

    @Singleton
    @Binds
    abstract fun provideDetailLocalDataSource(detailMovieLocalDataSource: DetailLocalDataSource): DetailMovieDataSource.Local

    @Singleton
    @Binds
    abstract fun provideDetailRemoteDataSource(detailMovieRemoteDataSource: DetailMovieRemoteDataSource): DetailMovieDataSource.Remote

    @Singleton
    @Binds
    abstract fun provideFavoriteLocalDataSource(favoriteLocalDataSource: FavoriteLocalDataSource): FavoriteDataSource.Local

    @Singleton
    @Binds
    abstract fun provideFavoriteRemoteDataSource(favoriteRemoteDataSource: FavoriteRemoteDataSource): FavoriteDataSource.Remote
}
