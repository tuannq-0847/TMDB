package com.rikkeisoft.moviedb.ui.moviehome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import com.rikkeisoft.moviedb.data.repository.MovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.handleData
import javax.inject.Inject

class MovieHomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _playingMovies by lazy { MutableLiveData<GetMovieListResponse>() }
    val playingMovies: LiveData<GetMovieListResponse>
        get() = _playingMovies

    private val _topRatedMovies by lazy { MutableLiveData<GetMovieListResponse>() }
    val topRatedMovies: LiveData<GetMovieListResponse>
        get() = _topRatedMovies

    private val _popularMovies by lazy { MutableLiveData<GetMovieListResponse>() }
    val popularMovies: LiveData<GetMovieListResponse>
        get() = _popularMovies

    private val _genres by lazy { MutableLiveData<GenreResponse>() }
    val genres: LiveData<GenreResponse>
        get() = _genres

    fun getData() {
        compositeDisposable.addAll(
            movieRepository.getPlayingFilms().handleData(_playingMovies, error, loading),
            movieRepository.getTopRatedFilms().handleData(_topRatedMovies, error, loading),
            movieRepository.getPopularFilms().handleData(_popularMovies, error, loading),
            movieRepository.getMovieGenresFilms().handleData(_genres, error, loading)
        )
    }

    companion object {
        const val NAME = "MovieHomeViewModel"
    }
}
