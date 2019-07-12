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

    override fun queryMovieInFavoriteById(idMovie: Int): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertFavoriteMovie(movieResult: MovieResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteFavoriteMovie(movieId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCreditsByIdMovie(id: Int): Single<CastDetailMovie> =
        detailMovieRemoteDataSource.getCreditsByIdMovie(id)

    override fun getSimilarsMovieById(id: Int): Single<GetMovieListResponse> =
        detailMovieRemoteDataSource.getSimilarsMovieById(id)
}
