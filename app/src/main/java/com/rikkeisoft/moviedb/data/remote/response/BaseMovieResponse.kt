package com.rikkeisoft.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName

open class BaseMovieResponse<T>(
    @SerializedName("page")
    var page: Int = 1,
    @SerializedName("total_results")
    var totalResult: Int = 1,
    @SerializedName("total_pages")
    var totalPages: Int = 1,
    @SerializedName("results")
    var results: MutableList<T> = mutableListOf()
)
