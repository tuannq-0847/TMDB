package com.rikkeisoft.moviedb.data.local.source

import com.rikkeisoft.moviedb.data.DetailMovieDataSource
import com.rikkeisoft.moviedb.data.local.dao.FavoriteDao
import com.rikkeisoft.moviedb.data.model.MovieResult
import io.reactivex.Single
import javax.inject.Inject

class DetailLocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao) : DetailMovieDataSource.Local {
    override fun queryMovieInFavorite(idMovie: Int): Single<Int> =
        favoriteDao.queryMovieInFavorite(idMovie)

    override fun insertFavoriteMovie(movieResult: MovieResult) = favoriteDao.saveFavorite(movieResult)

    override fun deleteFavoriteMovie(movieId: Int) = favoriteDao.deleteFavorite(movieId)
}