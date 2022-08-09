package com.karleinstein.moviedb.ui.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieParent
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.data.repository.MovieRepository
import com.karleinstein.moviedb.ui.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val application: Application,
    private val movieRepository: MovieRepository
) : CoreViewModel() {

    val searchResults by lazy { MutableLiveData<MutableList<MovieParent>>() }
    val castDetails by lazy { MutableLiveData<MutableList<SearchResult>>() }

    fun getResult(query: String) {
        compositeDisposable.add(
            movieRepository.getResultBySearch(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(ONE_SECONDS, MILLISECONDS)
                .map {
                    it.results
                }
                .subscribe({
                    val movieParents: MutableList<MovieParent> = ArrayList()
                    val listMovie = it.filter { searchResult ->
                        searchResult.mediaType == MOVIE
                    }
                    val listTv = it.filter { searchResult ->
                        searchResult.mediaType == TV
                    }
                    val people = it.filter { searchResult ->
                        searchResult.mediaType == PERSON
                    } as MutableList<SearchResult>
                    movieParents.add(
                        MovieParent(
                            application.getString(R.string.movies),
                            listMovie as MutableList<MovieResult>
                        )
                    )
                    movieParents.add(
                        MovieParent(
                            application.getString(R.string.tv_series),
                            listTv as MutableList<MovieResult>
                        )
                    )
                    searchResults.value = movieParents
                    castDetails.value = people
                }, {
                    setError(it)
                })
        )
    }

    companion object {
        const val ONE_SECONDS: Long = 1000
        const val NAME = "SearchViewModel"
        const val MOVIE = "movie"
        const val TV = "tv"
        const val PERSON = "person"
    }
}
