package com.karleinstein.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    var idGenre: Int = 1,
    @SerializedName("name")
    var nameGenre: String = "default"
)
