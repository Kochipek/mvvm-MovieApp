package com.kochipek.moviesmvvm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kochipek.moviesmvvm.data.model.Movie
import com.kochipek.moviesmvvm.data.source.local.db.MovieDatabase

import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    val movieLiveData = MutableLiveData<Movie>()
    fun getDataFromRoomDb(uuid : Int, context : Context) {
      viewModelScope.launch {
          val movieDao = MovieDatabase(context).movieDao()
            val movie = movieDao.getMovie(uuid)
            movieLiveData.value = movie
      }
    }
}