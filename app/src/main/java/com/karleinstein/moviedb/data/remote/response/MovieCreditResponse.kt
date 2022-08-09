package com.karleinstein.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.karleinstein.moviedb.data.model.MovieResult

class MovieCreditResponse(

    @SerializedName("cast")
    var movieResult: MutableList<MovieResult> = mutableListOf()
)
