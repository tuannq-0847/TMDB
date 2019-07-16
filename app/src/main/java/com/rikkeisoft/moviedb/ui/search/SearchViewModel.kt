package com.rikkeisoft.moviedb.ui.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieParent
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.data.repository.SearchRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    application: Application
) : BaseViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    val searchResults by lazy { MutableLiveData<MutableList<MovieParent>>() }
    val castDetails by lazy { MutableLiveData<MutableList<SearchResult>>() }

    fun getResult(query: String) {
        compositeDisposable.add(
            searchRepository.getResultBySearch(query)
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
                            context.getString(R.string.movies),
                            listMovie as MutableList<MovieResult>
                        )
                    )
                    movieParents.add(
                        MovieParent(
                            context.getString(R.string.tv_series),
                            listTv as MutableList<MovieResult>
                        )
                    )
                    searchResults.value = movieParents
                    castDetails.value = people
                }, {
                    error.value = it
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
