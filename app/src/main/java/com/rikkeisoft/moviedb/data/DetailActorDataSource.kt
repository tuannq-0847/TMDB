package com.rikkeisoft.moviedb.data

import com.rikkeisoft.moviedb.data.model.CastDetail
import com.rikkeisoft.moviedb.data.remote.response.MovieCreditResponse
import io.reactivex.Single

interface DetailActorDataSource {
    interface Remote {
        fun getActorDetails(id: Int): Single<CastDetail>
        fun getMovieCredit(id: Int): Single<MovieCreditResponse>
    }

    interface Local
}