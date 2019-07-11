package com.rikkeisoft.moviedb.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.repository.DetailMovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
    application: Application
) : BaseViewModel(application) {

    val titleMovie by lazy { MutableLiveData<String>() }
    val overview by lazy { MutableLiveData<String>() }
    val releaseDate by lazy { MutableLiveData<String>() }

    fun setDetailMovie(movieResult: MovieResult) {
        titleMovie.value = movieResult.titleMovie
        overview.value = movieResult.overView
        releaseDate.value = movieResult.releaseDate
    }

    fun getDetail(idMovie: Int) {
    }

    companion object {
        const val NAME = "DetailViewModel"
    }
}
