package com.rikkeisoft.moviedb.data.local.source

import com.rikkeisoft.moviedb.data.MovieDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieLocalDataSource @Inject constructor(): MovieDataSource.Local
