package com.rikkeisoft.moviedb.data.remote.source

import com.rikkeisoft.moviedb.data.DetailActorDataSource
import com.rikkeisoft.moviedb.data.model.CastDetail
import com.rikkeisoft.moviedb.data.remote.ApiService
import com.rikkeisoft.moviedb.data.remote.response.MovieCreditResponse
import io.reactivex.Single
import javax.inject.Inject

class DetailActorRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : DetailActorDataSource.Remote {

    override fun getMovieCredit(id: Int): Single<MovieCreditResponse> = apiService.getMovieCredit(id)

    override fun getActorDetails(id: Int): Single<CastDetail> = apiService.getDetailActor(id)
}
