package com.kochipek.moviesmvvm.service

import com.kochipek.moviesmvvm.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getAllMovies(@Query("api_key") apiKey : String): Response<MovieResponse>
}
