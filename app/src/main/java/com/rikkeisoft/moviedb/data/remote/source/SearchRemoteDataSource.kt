package com.rikkeisoft.moviedb.data.remote.source

import com.rikkeisoft.moviedb.data.SearchDataSource
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.data.remote.ApiService
import com.rikkeisoft.moviedb.data.remote.response.BaseMovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRemoteDataSource @Inject constructor(private val apiService: ApiService) : SearchDataSource.Remote {

    override fun getResultBySearch(query: String): Observable<BaseMovieResponse<SearchResult>> = apiService.getResultBySearch(query)
}
