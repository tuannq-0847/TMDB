package com.rikkeisoft.moviedb.ui.moviehome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.remote.response.APIResponse
import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import com.rikkeisoft.moviedb.data.repository.MovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.handleData
import javax.inject.Inject

class MovieHomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _movieData by lazy { MutableLiveData<APIResponse<GetMovieListResponse>>() }
    val movieData: LiveData<APIResponse<GetMovieListResponse>>
        get() = _movieData

    private val _genres by lazy { MutableLiveData<APIResponse<GenreResponse>>() }
    val genres: LiveData<APIResponse<GenreResponse>>
        get() = _genres

    fun getData() {
        compositeDisposable.addAll(
            movieRepository.getPlayingFilms().handleData(_movieData),
            movieRepository.getTopRatedFilms().handleData(_movieData),
            movieRepository.getPopularFilms().handleData(_movieData),
            movieRepository.getMovieGenresFilms().handleData(_genres)
        )
    }

    companion object {
        const val NAME = "MovieHomeViewModel"
    }
}
