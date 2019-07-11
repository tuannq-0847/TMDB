package com.rikkeisoft.moviedb.data.remote.source

import com.rikkeisoft.moviedb.data.DetailMovieDataSource
import com.rikkeisoft.moviedb.data.model.CastDetailMovie
import com.rikkeisoft.moviedb.data.remote.ApiService
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import io.reactivex.Single
import javax.inject.Inject

class DetailMovieRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    DetailMovieDataSource.Remote {

    override fun getCreditsByIdMovie(id: Int): Single<CastDetailMovie> = apiService.getCreditsByIdMovie(id)

    override fun getSimilarsMovieById(id: Int): Single<GetMovieListResponse> = apiService.getSimilarsByIdMovie(id)
}
