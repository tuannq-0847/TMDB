package com.rikkeisoft.moviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rikkeisoft.moviedb.data.local.FavoriteDatabase.Companion.VERSION_DATABASE
import com.rikkeisoft.moviedb.data.local.dao.FavoriteDao
import com.rikkeisoft.moviedb.data.model.MovieResult

@Database(entities = [MovieResult::class], version = VERSION_DATABASE, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val VERSION_DATABASE = 1
    }
}


//test ci
