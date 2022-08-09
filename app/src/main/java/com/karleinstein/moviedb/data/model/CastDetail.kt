package com.karleinstein.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class CastDetail(
    @SerializedName("birthday")
    var birthday: String = "",
    @SerializedName("known_for_department")
    var department: String = "",
    @SerializedName("place_of_birth")
    var placeBirth: String = "",
    @SerializedName("profile_path")
    var profilePath: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("biography")
    var biography: String = "",
    @SerializedName("also_known_as")
    var knownAs: MutableList<String> = mutableListOf(),
    var imageSize: String = "original"
)
