package com.rikkeisoft.moviedb.data.remote.source

import com.rikkeisoft.moviedb.data.MovieDataSource
import com.rikkeisoft.moviedb.data.remote.ApiService
import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Single

class MovieRemoteDataSource(private val apiService: ApiService) : MovieDataSource.Remote {
    override fun getPopularFilms(): Single<GetMovieListResponse> = apiService.getPopularFilms()

    override fun getTopRatedFilms(): Single<GetMovieListResponse> = apiService.getTopRatedFilms()

    override fun getPlayingFilms(): Single<GetMovieListResponse> = apiService.getPlayingFilms()

    override fun getMovieGenresFilms(): Single<GenreResponse> = apiService.getGenreMovies()
}
