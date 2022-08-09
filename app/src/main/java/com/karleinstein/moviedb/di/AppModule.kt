package com.karleinstein.moviedb.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.karleinstein.moviedb.data.local.FavoriteDatabase
import com.karleinstein.moviedb.data.local.dao.FavoriteDao
import com.karleinstein.moviedb.data.local.pref.AppPref
import com.karleinstein.moviedb.data.remote.ApiService
import com.karleinstein.moviedb.data.repository.MovieRepository
import com.karleinstein.moviedb.data.repository.MovieRepositoryImpl
import com.karleinstein.moviedb.service.NetworkService
import com.karleinstein.moviedb.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRepository(
        @ApplicationContext appContext: Context,
        apiService: ApiService,
        favoriteDao: FavoriteDao
    ): MovieRepository = MovieRepositoryImpl(appContext, apiService, favoriteDao)

    @Singleton
    @Provides
    fun provideApi() = NetworkService().createAppRetrofit()

    @Singleton
    @Provides
    fun provideFavoriteDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        FavoriteDatabase::class.java,
        Constants.TABLE_FAVORITE
    ).build()

    @Singleton
    @Provides
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase) = favoriteDatabase.favoriteDao()

    @Singleton
    @Provides
    fun provideAppPref(
        application: Application,
    ) = AppPref(application)
}