package com.rikkeisoft.moviedb.data.repository

import com.rikkeisoft.moviedb.data.FavoriteDataSource
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteLocalDataSource: FavoriteDataSource.Local,
    private val favoriteRemoteDataSource: FavoriteDataSource.Remote
) : FavoriteDataSource.Local, FavoriteDataSource.Remote {

    override fun getAllFavorites() = favoriteLocalDataSource.getAllFavorites()
}
