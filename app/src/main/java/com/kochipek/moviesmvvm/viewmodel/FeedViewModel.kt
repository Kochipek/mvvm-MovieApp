package com.kochipek.moviesmvvm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kochipek.moviesmvvm.data.model.Movie
import com.kochipek.moviesmvvm.data.source.local.db.MovieDatabase
import com.kochipek.moviesmvvm.data.source.remote.MovieAPIService
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
class FeedViewModel : ViewModel() {
    var job : Job? = null
    private val movieAPIService = MovieAPIService()
    var movies = MutableLiveData<List<Movie>>()
    var loadingState = MutableLiveData<Boolean>()

    fun loadData(context: Context) {
        loadingState.value = true
        job = viewModelScope.launch {
            try {
                val response = movieAPIService.getData()
                if (response.isSuccessful) {
                    response.body()?.let {
                        storeDataLocally(it.results, context)
                    }
                } else {
                    loadingState.value = false
                }
                loadingState.value = false
                        } catch (e: Exception) {
                               loadingState.value = false
            }
        }
        }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    private fun storeDataLocally(moviesList: List<Movie> , context: Context) {
        viewModelScope.launch {
            val movieDao = MovieDatabase(context).movieDao()
            movieDao.deleteAllMovies()
            val listLong = movieDao.insertAllMovies(*moviesList.toTypedArray())
            var i = 0
            while (i < moviesList.size) {
                moviesList[i].uuid = listLong[i].toInt()
                i += 1
            }
            moviesRetrieved(moviesList)
        }
    }
    private fun moviesRetrieved(moviesList: List<Movie>) {
        movies.value = moviesList
        loadingState.value = false

    }
}