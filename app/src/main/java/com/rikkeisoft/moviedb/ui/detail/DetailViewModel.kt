package com.rikkeisoft.moviedb.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.model.CastDetailMovie.CastDetail
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.repository.DetailMovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.Constants
import com.rikkeisoft.moviedb.utils.handleLoading
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
    application: Application
) : BaseViewModel(application) {

    val titleMovie by lazy { MutableLiveData<String>() }
    val overview by lazy { MutableLiveData<String>() }
    val releaseDate by lazy { MutableLiveData<String>() }
    val posterImage by lazy { MutableLiveData<String>() }
    val imageSize by lazy { MutableLiveData<String>() }
    val imageSizeBlur by lazy { MutableLiveData<String>() }
    val casters by lazy { MutableLiveData<MutableList<CastDetail>>() }
    val similarMovies by lazy { MutableLiveData<MutableList<MovieResult>>() }
    val voteRating by lazy { MutableLiveData<Double>() }

    fun setDetailMovie(movieResult: MovieResult) {
        titleMovie.value = movieResult.titleMovie
        overview.value = movieResult.overView
        releaseDate.value = movieResult.releaseDate
        posterImage.value = movieResult.posterPath
        imageSize.value = Constants.SIZE_W185
        imageSizeBlur.value = Constants.SIZE_ORIGINAL
        voteRating.value = movieResult.voteAverage
    }

    fun getDetail(idMovie: Int) {
        compositeDisposable.addAll(
            detailMovieRepository.getCreditsByIdMovie(idMovie)
                .handleLoading(loading)
                .map {
                    it.casters
                }
                .subscribe({
                    casters.value = it
                }, {
                    error.value = it
                }),
            detailMovieRepository.getSimilarsMovieById(idMovie)
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

    companion object {
        const val NAME = "DetailViewModel"
    }
}
