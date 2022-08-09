package com.karleinstein.moviedb.data.repository

import com.karleinstein.moviedb.data.model.CastDetail
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.data.remote.response.BaseMovieResponse
import com.karleinstein.moviedb.data.remote.response.CastDetailMovieResponse
import com.karleinstein.moviedb.data.remote.response.GenreResponse
import com.karleinstein.moviedb.data.remote.response.GetMovieListResponse
import com.karleinstein.moviedb.data.remote.response.MovieCreditResponse
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository{
     
     fun getPopularFilms(): Single<GetMovieListResponse>
     fun getTopRatedFilms(): Single<GetMovieListResponse>
     fun getPlayingFilms(): Single<GetMovieListResponse>
     fun getMovieGenresFilms(): Single<GenreResponse>
     fun getMovieCredit(id: Int): Single<MovieCreditResponse>
     fun getActorDetails(id: Int): Single<CastDetail>
     fun getAllFavorites(): Single<MutableList<MovieResult>>
     fun getResultBySearch(query: String): Observable<BaseMovieResponse<SearchResult>>
     fun getCreditsByIdMovie(id: Int): Single<CastDetailMovieResponse>
     fun getSimilarsMovieById(id: Int): Single<GetMovieListResponse>
}
