package com.rikkeisoft.moviedb.data.repository

import com.rikkeisoft.moviedb.data.DetailActorDataSource
import com.rikkeisoft.moviedb.data.model.CastDetail
import com.rikkeisoft.moviedb.data.remote.response.MovieCreditResponse
import io.reactivex.Single
import javax.inject.Inject

class DetailActorRepository @Inject constructor(
    private val detailActorLocalDataSource: DetailActorDataSource.Local,
    private val detailActorRemoteDataSource: DetailActorDataSource.Remote
) : DetailActorDataSource.Local, DetailActorDataSource.Remote {

    override fun getMovieCredit(id: Int): Single<MovieCreditResponse> = detailActorRemoteDataSource.getMovieCredit(id)

    override fun getActorDetails(id: Int): Single<CastDetail> = detailActorRemoteDataSource.getActorDetails(id)
}
