package com.rikkeisoft.moviedb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rikkeisoft.moviedb.data.local.FavoriteDatabase.Companion.VERSION_DATABASE
import com.rikkeisoft.moviedb.data.local.dao.FavoriteDao
import com.rikkeisoft.moviedb.data.model.MovieResult

@Database(entities = [MovieResult::class], version = VERSION_DATABASE)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val VERSION_DATABASE = 1
        private const val FAVORITE_DB = "favorite.db"

        @Volatile
        private var instance: FavoriteDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance
                ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FavoriteDatabase::class.java,
                FAVORITE_DB
            ).build()
    }
}
