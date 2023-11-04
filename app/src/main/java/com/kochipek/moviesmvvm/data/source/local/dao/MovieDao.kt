package com.kochipek.moviesmvvm.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kochipek.moviesmvvm.data.model.Movie

@Dao
interface MovieDao {
    @Insert // db'e erisim metodu
    suspend fun insertAllMovies(vararg movies: Movie) : List<Long>
    // suspend ->  pause/resume
    // vararg -> degisken sayida parametre alabilir. Liste vermek olmaz cunku parametreleri tek tek vermemiz gerekiyor.
    // List<Long> -> insert edilen uuid'leri dondurur.

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies() : List<Movie>
    @Query("SELECT * FROM movie WHERE uuid = :uuid")
    suspend fun getMovie(uuid: Int) : Movie

    @Query("DELETE FROM movie") // db'den tum verileri siler.
    suspend fun deleteAllMovies()

    @Query("DELETE FROM movie WHERE uuid = :uuid") // db'den uuid'si verilen veriyi siler.
    suspend fun deleteMovie(uuid: Int)

}
