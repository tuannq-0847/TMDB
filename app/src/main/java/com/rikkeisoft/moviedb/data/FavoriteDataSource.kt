package com.rikkeisoft.moviedb.data

interface FavoriteDataSource {
    interface Remote

    interface Local {
        fun getAllFavorites()
    }
}