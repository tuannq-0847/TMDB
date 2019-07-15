package com.rikkeisoft.moviedb.data.local.source

import com.rikkeisoft.moviedb.data.FavoriteDataSource
import com.rikkeisoft.moviedb.data.local.dao.FavoriteDao
import com.rikkeisoft.moviedb.data.model.MovieResult
import io.reactivex.Single
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao) : FavoriteDataSource.Local {
    override fun getAllFavorites(): Single<MutableList<MovieResult>> = favoriteDao.getAllFavorites()
}
