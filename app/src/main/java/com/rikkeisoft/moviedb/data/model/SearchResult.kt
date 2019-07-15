package com.rikkeisoft.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("first_air_date")
    var firstAirDate: String = "",
    @SerializedName("media_type")
    var mediaType: String = "",
    @SerializedName("cast_id")
    var castId: Int = 0,
    @SerializedName("name")
    var nameCaster: String = "",
    @SerializedName("character")
    var characterName: String = "",
    @SerializedName("profile_path")
    var profilePath: String = "",
    @SerializedName("known_for")
    var knownFor: MutableList<MovieResult> = mutableListOf()
) : MovieResult()
