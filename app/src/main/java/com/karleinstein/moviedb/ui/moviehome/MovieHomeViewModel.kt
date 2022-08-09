package com.karleinstein.moviedb.ui.moviehome

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieParent
import com.karleinstein.moviedb.data.remote.response.GenreResponse
import com.karleinstein.moviedb.data.remote.response.GetMovieListResponse
import com.karleinstein.moviedb.data.repository.MovieRepository
import com.karleinstein.moviedb.ui.CoreViewModel
import com.karleinstein.moviedb.utils.handleLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val application: Application,
    private val movieRepository: MovieRepository
) : CoreViewModel() {

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
                this
            ).subscribe({
                _movieData.value = it
            }, {
                setError(it)
            }),
            movieRepository.getMovieGenresFilms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _genres.value = it
                }, {
                    setError(it)
                })
        )
    }

    private fun zipSingle(
        response1: GetMovieListResponse,
        response2: GetMovieListResponse,
        response3: GetMovieListResponse
    ): MutableList<MovieParent> {
        val movieParents: MutableList<MovieParent> = ArrayList()
        movieParents.add(
            MovieParent(
                application.resources.getString(R.string.theater),
                response1.results
            )
        )
        movieParents.add(
            MovieParent(
                application.resources.getString(R.string.popular),
                response3.results
            )
        )
        movieParents.add(
            MovieParent(
                application.resources.getString(R.string.top_rate),
                response2.results
            )
        )
        return movieParents
    }

    companion object {
        const val NAME = "MovieHomeViewModel"
    }
}
