package com.karleinstein.moviedb.data.repository

import android.content.Context
import com.karleinstein.basemvvm.data.source.BaseRepositoryImpl
import com.karleinstein.moviedb.data.local.dao.FavoriteDao
import com.karleinstein.moviedb.data.model.CastDetail
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.data.remote.ApiService
import com.karleinstein.moviedb.data.remote.response.BaseMovieResponse
import com.karleinstein.moviedb.data.remote.response.CastDetailMovieResponse
import com.karleinstein.moviedb.data.remote.response.GenreResponse
import com.karleinstein.moviedb.data.remote.response.GetMovieListResponse
import com.karleinstein.moviedb.data.remote.response.MovieCreditResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    context: Context,
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
) : BaseRepositoryImpl(context), MovieRepository {
    override fun getPopularFilms(): Single<GetMovieListResponse> {
        return apiService.getPopularFilms()
    }

    override fun getTopRatedFilms(): Single<GetMovieListResponse> {
        return apiService.getTopRatedFilms()
    }

    override fun getPlayingFilms(): Single<GetMovieListResponse> {
        return apiService.getPlayingFilms()
    }

    override fun getMovieGenresFilms(): Single<GenreResponse> {
        return apiService.getGenreMovies()
    }

    override fun getMovieCredit(id: Int): Single<MovieCreditResponse> {
        return apiService.getMovieCredit(id)
    }

    override fun getActorDetails(id: Int): Single<CastDetail> {
        return apiService.getDetailActor(id)
    }

    override fun getAllFavorites(): Single<MutableList<MovieResult>> {
        return favoriteDao.getAllFavorites()
    }

    override fun getResultBySearch(query: String): Observable<BaseMovieResponse<SearchResult>> {
        return apiService.getResultBySearch(query)
    }

    override fun getCreditsByIdMovie(id: Int): Single<CastDetailMovieResponse> {
        return apiService.getCreditsByIdMovie(id)
    }

    override fun getSimilarsMovieById(id: Int): Single<GetMovieListResponse> {
        return apiService.getSimilarsByIdMovie(id)
    }

}