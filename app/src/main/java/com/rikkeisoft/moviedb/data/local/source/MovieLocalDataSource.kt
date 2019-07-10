package com.rikkeisoft.moviedb.data.local.source

import com.rikkeisoft.moviedb.data.MovieDataSource
import com.rikkeisoft.moviedb.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(private val apiService: ApiService) : MovieDataSource.Local
