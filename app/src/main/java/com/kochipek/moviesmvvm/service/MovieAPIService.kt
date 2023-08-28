package com.kochipek.moviesmvvm.service

import com.kochipek.moviesmvvm.model.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {
    private val BaseUrl = "https://api.themoviedb.org/3/"
    val apiKey = "43cf09ac0f4b550009ffed8bc2834096"
    private val api = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    suspend fun getData(): Response<MovieResponse> {
        return api.getAllMovies(apiKey)
    }

}