package com.kochipek.moviesmvvm.model

data class Movie(
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: String?,
    val release_date: String?
)

data class MovieResponse(val results : List<Movie>)