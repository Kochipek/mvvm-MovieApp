package com.kochipek.moviesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kochipek.moviesmvvm.data.model.Movie

class MovieViewModel : ViewModel() {
    val movieLiveData = MutableLiveData<Movie>()

    //TODO get data from room db
    fun getMovieData() {
        val movie = Movie("dummy title", "overview", "poster", "imdb_score", "release_date")
        movieLiveData.value = movie
    }
}