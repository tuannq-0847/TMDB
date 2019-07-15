package com.rikkeisoft.moviedb.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.model.SearchResult
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
    val casters by lazy { MutableLiveData<MutableList<SearchResult>>() }
    val similarMovies by lazy { MutableLiveData<MutableList<MovieResult>>() }
    val favoriteResult by lazy { MutableLiveData<Boolean>() }

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
                }),
            detailMovieRepository.getCountRowMovieById(movieResult.idMovie)
                .handleLoading(loading)
                .subscribe({
                    movieResult.isFavorite = it > 0
                    movie.value = movieResult
                }, {

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
                        favoriteResult.postValue(false)
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
                        favoriteResult.postValue(true)
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
