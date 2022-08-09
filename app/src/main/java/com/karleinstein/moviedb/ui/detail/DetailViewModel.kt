package com.karleinstein.moviedb.ui.detail

import androidx.lifecycle.MutableLiveData
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.data.repository.MovieRepository
import com.karleinstein.moviedb.ui.CoreViewModel
import com.karleinstein.moviedb.utils.handleLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailMovieRepository: MovieRepository
) : CoreViewModel() {

    val movie by lazy { MutableLiveData<MovieResult>() }
    val casters by lazy { MutableLiveData<MutableList<SearchResult>>() }
    val similarMovies by lazy { MutableLiveData<MutableList<MovieResult>>() }
    val favoriteResult = MutableLiveData<Boolean>()
    fun getDetail(movieResult: MovieResult) {
        movie.value = movieResult
        compositeDisposable.addAll(
            detailMovieRepository.getCreditsByIdMovie(movieResult.idMovie)
                .handleLoading(this)
                .map {
                    it.casters
                }
                .subscribe({
                    casters.value = it
                }, {
                    setError(it)
                }),
            detailMovieRepository.getSimilarsMovieById(movieResult.idMovie)
                .handleLoading(this)
                .map {
                    it.results
                }
                .subscribe({
                    similarMovies.value = it
                }, {
                    setError(it)
                }),
//            detailMovieRepository.getCountRowMovieById(movieResult.idMovie)
//                .handleLoading(this)
//                .subscribe({
//                    movieResult.isFavorite = it > 0
//                    movie.value = movieResult
//                }, {
//
//                })
        )
    }

    fun onClickFavoriteItem(isFavorite: Boolean) {
        val favoriteMovie = movie.value!!
        if (isFavorite) {
            compositeDisposable.add(
                Completable.fromAction {
//                    detailMovieRepository.deleteFavoriteMovie(favoriteMovie.idMovie)
                }.observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showLoading(true)
                    }
                    .doOnTerminate { showLoading(false) }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        favoriteMovie.isFavorite = false
                        movie.postValue(favoriteMovie)
                        favoriteResult.postValue(false)
                    }, {
                        setError(it)
                    })
            )
        } else {
            compositeDisposable.add(
                Completable.fromAction {
//                    detailMovieRepository.insertFavoriteMovie(favoriteMovie)
                }.observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showLoading(true)
                    }
                    .doOnTerminate { showLoading(false) }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        favoriteMovie.isFavorite = true
                        movie.postValue(favoriteMovie)
                        favoriteResult.postValue(true)
                    }, {
                        setError(it)
                    })
            )
        }
    }

    companion object {
        const val NAME = "DetailViewModel"
    }
}
