package com.rikkeisoft.moviedb.di

import android.app.Application
import androidx.room.Room
import com.rikkeisoft.moviedb.data.local.FavoriteDatabase
import com.rikkeisoft.moviedb.data.local.pref.AppPref
import com.rikkeisoft.moviedb.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

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
    fun provideAppSharedPref(application: Application) = AppPref(application)
}
