package com.karleinstein.moviedb.data.remote

import com.karleinstein.moviedb.data.model.CastDetail
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.data.remote.response.BaseMovieResponse
import com.karleinstein.moviedb.data.remote.response.CastDetailMovieResponse
import com.karleinstein.moviedb.data.remote.response.GenreResponse
import com.karleinstein.moviedb.data.remote.response.GetMovieListResponse
import com.karleinstein.moviedb.data.remote.response.MovieCreditResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    fun getPopularFilms(): Single<GetMovieListResponse>

    @GET("3/movie/top_rated")
    fun getTopRatedFilms(): Single<GetMovieListResponse>

    @GET("3/movie/now_playing")
    fun getPlayingFilms(): Single<GetMovieListResponse>

    @GET("3/genre/movie/list")
    fun getGenreMovies(): Single<GenreResponse>

    @GET("3/movie/{id}/credits")
    fun getCreditsByIdMovie(@Path("id") id: Int): Single<CastDetailMovieResponse>

    @GET("3/search/multi")
    fun getResultBySearch(@Query("query") query: String): Observable<BaseMovieResponse<SearchResult>>

    @GET("3/movie/{id}/similar")
    fun getSimilarsByIdMovie(@Path("id") id: Int): Single<GetMovieListResponse>

    @GET("3/person/{id}")
    fun getDetailActor(@Path("id") id: Int): Single<CastDetail>

    @GET("3/person/{id}/movie_credits")
    fun getMovieCredit(@Path("id") id: Int): Single<MovieCreditResponse>
}
