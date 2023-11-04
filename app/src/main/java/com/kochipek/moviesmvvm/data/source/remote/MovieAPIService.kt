package com.kochipek.moviesmvvm.data.source.remote

import com.kochipek.moviesmvvm.data.model.MovieResponse
import com.kochipek.moviesmvvm.utils.Utils
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Utils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    suspend fun getData(): Response<MovieResponse> {
        return api.getAllMovies(Utils.API_KEY)
    }

}