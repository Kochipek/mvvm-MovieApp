package com.kochipek.moviesmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kochipek.moviesmvvm.data.model.Movie
import com.kochipek.moviesmvvm.data.source.remote.MovieAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel : ViewModel() {
    var job : Job? = null
    private val movieAPIService = MovieAPIService()
    var movies = MutableLiveData<List<Movie>>()
    var movieError = MutableLiveData<Boolean>()
    var loadingState = MutableLiveData<Boolean>()

    // dummy data
    // load data from API with retrofit and coroutines
    fun loadData() {
        // Loading state başlat
        loadingState.value = true

        // CoroutineScope başlat
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                // Verileri API'den çek
                val response = movieAPIService.getData()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        movies.value = response.body()?.results
                        movieError.value = false
                    } else {
                        movieError.value = true
                    }
                    loadingState.value = false
                }
            } catch (e: Exception) {
                // Hata durumunda
                withContext(Dispatchers.Main) {
                    movieError.value = true
                    loadingState.value = false
                }
            }
        }
    }

    // ViewModel yok edildiğinde, işlemi iptal et
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
