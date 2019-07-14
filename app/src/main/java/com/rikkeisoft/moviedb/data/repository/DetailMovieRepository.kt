package com.rikkeisoft.moviedb.data.repository

import com.rikkeisoft.moviedb.data.DetailMovieDataSource
import com.rikkeisoft.moviedb.data.model.CastDetailMovie
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailMovieRepository @Inject constructor(
    private val detailMovieRemoteDataSource: DetailMovieDataSource.Remote,
    private val detailMovieLocalDataSource: DetailMovieDataSource.Local
) : DetailMovieDataSource.Remote, DetailMovieDataSource.Local {

    override fun insertFavoriteMovie(movieResult: MovieResult) =
        detailMovieLocalDataSource.insertFavoriteMovie(movieResult)

    override fun deleteFavoriteMovie(movieId: Int) = detailMovieLocalDataSource.deleteFavoriteMovie(movieId)

    override fun getCreditsByIdMovie(id: Int): Single<CastDetailMovie> =
        detailMovieRemoteDataSource.getCreditsByIdMovie(id)

    override fun getSimilarsMovieById(id: Int): Single<GetMovieListResponse> =
        detailMovieRemoteDataSource.getSimilarsMovieById(id)
}