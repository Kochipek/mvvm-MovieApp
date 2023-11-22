package com.kochipek.moviesmvvm.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kochipek.moviesmvvm.data.model.Movie

@Dao
interface MovieDao{
    @Insert 
    suspend fun insertAllMovies(vararg movies: Movie) : List<Long>
    @Query("SELECT * FROM movie")
    suspend fun getAllMovies() : List<Movie>
    @Query("SELECT * FROM movie WHERE uuid = :uuid")
    suspend fun getMovie(uuid: Int) : Movie

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("DELETE FROM movie WHERE uuid = :uuid")
    suspend fun deleteMovie(uuid: Int)

}
