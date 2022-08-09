package com.karleinstein.moviedb.ui.detail_actor

import androidx.lifecycle.MutableLiveData
import com.karleinstein.moviedb.data.model.CastDetail
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.repository.MovieRepository
import com.karleinstein.moviedb.ui.CoreViewModel
import com.karleinstein.moviedb.utils.handleLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DetailActorViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : CoreViewModel() {

    val actors by lazy { MutableLiveData<CastDetail>() }
    val movies by lazy { MutableLiveData<MutableList<MovieResult>>() }

    fun getDetailActor(idActor: Int) {
        compositeDisposable.add(
            movieRepository.getActorDetails(idActor)
                .handleLoading(this)
                .subscribe({
                    actors.value = it
                }, {
                    setError(it)
                })
        )
    }

    fun getMovieCredit(idActor: Int) {
        compositeDisposable.add(
            movieRepository.getMovieCredit(idActor)
                .handleLoading(this)
                .map {
                    it.movieResult
                }
                .subscribe({
                    movies.value = it
                }, {
                    setError(it)
                })
        )
    }

    companion object {
        const val NAME = "DetailActorViewModel"
    }
}
