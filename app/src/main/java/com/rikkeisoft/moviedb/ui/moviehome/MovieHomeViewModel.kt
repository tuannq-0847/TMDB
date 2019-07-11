package com.rikkeisoft.moviedb.ui.moviehome

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieParent
import com.rikkeisoft.moviedb.data.remote.response.GenreResponse
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import com.rikkeisoft.moviedb.data.repository.MovieRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.handleLoading
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class MovieHomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository, application: Application
) : BaseViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val _movieData by lazy { MutableLiveData<MutableList<MovieParent>>() }
    val movieData: LiveData<MutableList<MovieParent>>
        get() = _movieData

    private val _genres by lazy { MutableLiveData<GenreResponse>() }
    val genres: LiveData<GenreResponse>
        get() = _genres

    fun getData() {
        compositeDisposable.addAll(
            Single.zip(movieRepository.getPlayingFilms(),
                movieRepository.getTopRatedFilms(),
                movieRepository.getPopularFilms(),
                Function3<GetMovieListResponse, GetMovieListResponse, GetMovieListResponse, MutableList<MovieParent>> { t1, t2, t3 ->
                    return@Function3 zipSingle(t1, t2, t3)
                }
            ).handleLoading(
                loading
            ).subscribe({
                _movieData.value = it
            }, {
                error.value = it
            }),
            movieRepository.getMovieGenresFilms()
                .handleLoading(loading)
                .subscribe({
                    _genres.value = it
                }, {
                    error.value = it
                })
        )
    }

    private fun zipSingle(
        response1: GetMovieListResponse,
        response2: GetMovieListResponse,
        response3: GetMovieListResponse
    ): MutableList<MovieParent> {
        val movieParents: MutableList<MovieParent> = ArrayList()
        movieParents.add(MovieParent(context.resources.getString(R.string.theater), response1.results))
        movieParents.add(MovieParent(context.resources.getString(R.string.popular), response3.results))
        movieParents.add(MovieParent(context.resources.getString(R.string.top_rate), response2.results))
        return movieParents
    }

    companion object {
        const val NAME = "MovieHomeViewModel"
    }
}
