package com.rikkeisoft.moviedb.data.remote.source

import com.rikkeisoft.moviedb.data.MovieDataSource
import com.rikkeisoft.moviedb.data.remote.ApiService
import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) : MovieDataSource.Remote {

    override fun getPopularFilms(): Observable<GetMovieListResponse> = apiService.getPopularFilms()

    override fun getTopRatedFilms(): Observable<GetMovieListResponse> = apiService.getTopRatedFilms()

    override fun getPlayingFilms(): Observable<GetMovieListResponse> = apiService.getPlayingFilms()

    override fun getMovieGenresFilms(): Single<GenreResponse> = apiService.getGenreMovies()
}
