package com.kochipek.moviesmvvm.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kochipek.moviesmvvm.data.model.Movie
import com.kochipek.moviesmvvm.data.source.local.dao.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
            private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java, "moviedatabase"
            ).build()

    }
}
