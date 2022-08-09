package com.karleinstein.moviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karleinstein.moviedb.data.local.FavoriteDatabase.Companion.VERSION_DATABASE
import com.karleinstein.moviedb.data.local.dao.FavoriteDao
import com.karleinstein.moviedb.data.model.MovieResult

@Database(entities = [MovieResult::class], version = VERSION_DATABASE)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val VERSION_DATABASE = 1
    }
}
