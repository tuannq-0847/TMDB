package com.rikkeisoft.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.rikkeisoft.moviedb.data.model.SearchResult

data class CastDetailMovieResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("cast")
    var casters: MutableList<SearchResult> = mutableListOf()
)
