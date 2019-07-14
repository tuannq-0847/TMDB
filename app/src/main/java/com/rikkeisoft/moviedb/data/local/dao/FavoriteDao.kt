package com.rikkeisoft.moviedb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.utils.Constants.TABLE_FAVORITE
import io.reactivex.Single

@Dao
abstract class FavoriteDao {

    @Insert
    abstract fun saveFavorite(movie: MovieResult)

    @Query("delete from $TABLE_FAVORITE where idMovie =:idMovie")
    abstract fun deleteFavorite(idMovie: Int)
}
