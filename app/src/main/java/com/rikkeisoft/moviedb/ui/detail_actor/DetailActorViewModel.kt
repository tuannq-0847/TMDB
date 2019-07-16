package com.rikkeisoft.moviedb.ui.detail_actor

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.DetailActorDataSource
import com.rikkeisoft.moviedb.data.model.CastDetail
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.handleLoading
import javax.inject.Inject

class DetailActorViewModel @Inject constructor(
    private val remoteDetailActorDataSource: DetailActorDataSource.Remote,
    private val localDetailActorDataSource: DetailActorDataSource.Local,
    application: Application
) : BaseViewModel(application) {

    val actors by lazy { MutableLiveData<CastDetail>() }
    val movies by lazy { MutableLiveData<MutableList<MovieResult>>() }

    fun getDetailActor(idActor: Int) {
        compositeDisposable.add(
            remoteDetailActorDataSource.getActorDetails(idActor)
                .handleLoading(loading)
                .subscribe({
                    actors.value = it
                }, {
                    error.value = it
                })
        )
    }

    fun getMovieCredit(idActor: Int) {
        compositeDisposable.add(
            remoteDetailActorDataSource.getMovieCredit(idActor)
                .handleLoading(loading)
                .map {
                    it.movieResult
                }
                .subscribe({
                    movies.value = it
                }, {
                    error.value = it
                })
        )
    }

    companion object {
        const val NAME = "DetailActorViewModel"
    }
}
