package com.kochipek.moviesmvvm.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kochipek.moviesmvvm.data.model.Movie
import com.kochipek.moviesmvvm.data.source.local.dao.MovieDao

// birden fazla entity de olabilir. (Movie, User, ...)


// !----------------------- sor ----------------------- Movie::class, reflection
@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    // singleton olusturmamiz lazim cunku farkli threadlerden baska baska db instance'lar olusturulabilir. Bu da hata verebilir.
    companion object {
        // diger threadlerin de bu instance'a erisebilmesi icin volatile kullaniliyor.
        @Volatile
        private var instance: MovieDatabase? = null

        // synchronized -> ayni anda sadece bir thread'in bu fonksiyona erismesini saglar. (thread safe)

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            // check if instance is null then build database and assign to instance
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
