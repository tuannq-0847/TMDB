package com.rikkeisoft.moviedb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.rikkeisoft.moviedb.utils.Constants.TABLE_FAVORITE
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_FAVORITE)
@Parcelize
open class MovieResult(
    @SerializedName("vote_count")
    var voteCount: Int = 1,
    @SerializedName("id")
    @PrimaryKey
    var idMovie: Int = 1,
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("vote_average")
    var voteAverage: Double = 1.0,
    @SerializedName("title")
    var titleMovie: String = "title",
    @SerializedName("popularity")
    var popularity: Double = 1.0,
    @SerializedName("poster_path")
    var posterPath: String = "default",
    @SerializedName("original_title")
    var originalTitle: String = "title",
    @SerializedName("backdrop_path")
    var backDropPath: String = "default",
    @SerializedName("overview")
    var overView: String = "default",
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("original_name")
    var nameMovie: String? = null,
    val imageSize: String = "w185",
    val imageSizeBlur: String = "original",
    var isFavorite: Boolean? = false
) : Parcelable
