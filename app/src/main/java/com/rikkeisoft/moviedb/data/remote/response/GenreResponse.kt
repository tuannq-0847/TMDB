package com.rikkeisoft.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.rikkeisoft.moviedb.data.model.Genre

class GenreResponse {
    @SerializedName("genres")
    val genres: List<Genre> = mutableListOf()
}
