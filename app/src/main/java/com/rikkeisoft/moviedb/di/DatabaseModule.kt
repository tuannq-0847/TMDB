package com.rikkeisoft.moviedb.di

import android.content.Context
import com.rikkeisoft.moviedb.data.local.FavoriteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    @Singleton
    @Provides
    fun provideFavoriteDatabase(context: Context) = FavoriteDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase) = favoriteDatabase.favoriteDao()
}
