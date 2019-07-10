package com.rikkeisoft.moviedb.ui.moviehome

import com.rikkeisoft.moviedb.data.repository.MovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import javax.inject.Inject

class MovieHomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    companion object {
        const val NAME = "MovieHomeViewModel"
    }
}