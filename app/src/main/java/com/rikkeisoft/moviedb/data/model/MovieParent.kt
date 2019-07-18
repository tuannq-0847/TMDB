package com.rikkeisoft.moviedb.data.model

data class MovieParent(
    var kindOfMovie: String,
    var movieResults: MutableList<MovieResult> = mutableListOf()
)
