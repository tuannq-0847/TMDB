package com.rikkeisoft.moviedb.data

import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Observable
import io.reactivex.Single

interface MovieDataSource {
    interface Remote {
        fun getPopularFilms(): Single<GetMovieListResponse>
        fun getTopRatedFilms(): Single<GetMovieListResponse>
        fun getPlayingFilms(): Single<GetMovieListResponse>
        fun getMovieGenresFilms(): Single<GenreResponse>
    }

    interface Local
}
