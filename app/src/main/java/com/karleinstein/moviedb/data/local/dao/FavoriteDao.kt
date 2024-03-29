package com.karleinstein.moviedb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.utils.Constants.TABLE_FAVORITE
import io.reactivex.Single

@Dao
abstract class FavoriteDao {

    @Insert
    abstract fun saveFavorite(movie: MovieResult)

    @Query("delete from $TABLE_FAVORITE where idMovie =:idMovie")
    abstract fun deleteFavorite(idMovie: Int)

    @Query("select count(*) from $TABLE_FAVORITE where idMovie=:idMovie")
    abstract fun getCountMovieById(idMovie: Int): Single<Int>

    @Query("select * from $TABLE_FAVORITE")
    abstract fun getAllFavorites(): Single<MutableList<MovieResult>>
}
