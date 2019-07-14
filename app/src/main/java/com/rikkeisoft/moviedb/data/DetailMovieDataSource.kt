package com.rikkeisoft.moviedb.data

import com.rikkeisoft.moviedb.data.model.CastDetailMovie
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Single

interface DetailMovieDataSource {
    interface Remote {
        fun getCreditsByIdMovie(id: Int): Single<CastDetailMovie>
        fun getSimilarsMovieById(id: Int): Single<GetMovieListResponse>
    }

    interface Local {
        fun queryMovieInFavorite(idMovie: Int): Single<Int>
        fun insertFavoriteMovie(movieResult: MovieResult)
        fun deleteFavoriteMovie(movieId: Int)
    }
}
