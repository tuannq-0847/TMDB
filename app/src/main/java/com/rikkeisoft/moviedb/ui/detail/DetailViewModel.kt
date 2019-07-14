package com.rikkeisoft.moviedb.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.model.CastDetailMovie.CastDetail
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.repository.DetailMovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.handleLoading
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
    application: Application
) : BaseViewModel(application) {

    val movie by lazy { MutableLiveData<MovieResult>() }
    val casters by lazy { MutableLiveData<MutableList<CastDetail>>() }
    val similarMovies by lazy { MutableLiveData<MutableList<MovieResult>>() }
    var favoriteStatus = MutableLiveData<Boolean>()

    fun getDetail(movieResult: MovieResult) {
        movie.value = movieResult
        compositeDisposable.addAll(
            detailMovieRepository.getCreditsByIdMovie(movieResult.idMovie)
                .handleLoading(loading)
                .map {
                    it.casters
                }
                .subscribe({
                    casters.value = it
                }, {
                    error.value = it
                }),
            detailMovieRepository.getSimilarsMovieById(movieResult.idMovie)
                .handleLoading(loading)
                .map {
                    it.results
                }
                .subscribe({
                    similarMovies.value = it
                }, {
                    error.value = it
                })
        )
    }

    fun onClickFavoriteItem(isFavorite: Boolean) {
        val favoriteMovie = movie.value!!
        if (isFavorite) {
            compositeDisposable.add(
                Completable.fromAction {
                    detailMovieRepository.deleteFavoriteMovie(favoriteMovie.idMovie)
                }.observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        loading.postValue(true)
                    }
                    .doOnTerminate { loading.postValue(false) }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        favoriteMovie.isFavorite = false
                        movie.postValue(favoriteMovie)
                    }, {
                        error.postValue(it)
                    })
            )
        } else {
            compositeDisposable.add(
                Completable.fromAction {
                    detailMovieRepository.insertFavoriteMovie(favoriteMovie)
                }.observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        loading.postValue(true)
                    }
                    .doOnTerminate { loading.postValue(false) }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        favoriteMovie.isFavorite = true
                        movie.postValue(favoriteMovie)
                    }, {
                        error.postValue(it)
                    })
            )
        }
    }

    companion object {
        const val NAME = "DetailViewModel"
    }
}
