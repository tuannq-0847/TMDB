package com.rikkeisoft.moviedb.data

import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.data.remote.response.BaseMovieResponse
import io.reactivex.Observable
import io.reactivex.Single

interface SearchDataSource {
    interface Local

    interface Remote {
        fun getResultBySearch(query: String): Observable<BaseMovieResponse<SearchResult>>
    }
}
