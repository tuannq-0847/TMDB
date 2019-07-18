package com.rikkeisoft.moviedb.data.repository

import com.rikkeisoft.moviedb.data.SearchDataSource
import com.rikkeisoft.moviedb.data.local.source.SearchLocalDataSource
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.data.remote.response.BaseMovieResponse
import com.rikkeisoft.moviedb.data.remote.source.SearchRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchDataSource.Local, SearchDataSource.Remote {

    override fun getResultBySearch(query: String): Observable<BaseMovieResponse<SearchResult>> =
        searchRemoteDataSource.getResultBySearch(query)
}
