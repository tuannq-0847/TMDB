package com.rikkeisoft.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class CastDetailMovie(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("cast")
    var casters: MutableList<CastDetail> = mutableListOf()
) {

    data class CastDetail(
        @SerializedName("cast_id")
        var castId: Int = 0,
        @SerializedName("name")
        var nameCaster: String = "",
        @SerializedName("character")
        var characterName: String = "",
        @SerializedName("profile_path")
        var profilePath: String = ""
    )
}
