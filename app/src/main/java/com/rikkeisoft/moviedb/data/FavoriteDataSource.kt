package com.rikkeisoft.moviedb.data

import com.rikkeisoft.moviedb.data.model.MovieResult
import io.reactivex.Single

interface FavoriteDataSource {
    interface Remote

    interface Local {
        fun getAllFavorites(): Single<MutableList<MovieResult>>
    }
}