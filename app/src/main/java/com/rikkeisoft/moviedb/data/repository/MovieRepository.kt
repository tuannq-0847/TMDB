package com.rikkeisoft.moviedb.data.repository

import com.rikkeisoft.moviedb.data.MovieDataSource
import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteMovieDataSource: MovieDataSource.Remote,
    private val localMovieDataSource: MovieDataSource.Local
) : MovieDataSource.Local, MovieDataSource.Remote {

    override fun getPopularFilms(): Single<GetMovieListResponse> = remoteMovieDataSource.getPopularFilms()

    override fun getTopRatedFilms(): Single<GetMovieListResponse> = remoteMovieDataSource.getTopRatedFilms()

    override fun getPlayingFilms(): Single<GetMovieListResponse> = remoteMovieDataSource.getPlayingFilms()

    override fun getMovieGenresFilms(): Single<GenreResponse> = remoteMovieDataSource.getMovieGenresFilms()
}
