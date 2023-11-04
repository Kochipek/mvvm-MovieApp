package com.kochipek.moviesmvvm.data.source.remote

import com.kochipek.moviesmvvm.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getAllMovies(@Query("api_key") apiKey : String): Response<MovieResponse>
}
