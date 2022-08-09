package com.karleinstein.moviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.karleinstein.moviedb.data.model.Genre

class GenreResponse {
    @SerializedName("genres")
    val genres: MutableList<Genre> = mutableListOf()
}
