package com.example.bestmovies.topmovies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestmovies.Constants
import com.example.bestmovies.models.Movie
import com.example.bestmovies.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopMoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: MutableLiveData<List<Movie>> = _movies

    private val _status = MutableLiveData<String>()
    val status: MutableLiveData<String> = _status

    init {
        _status.value = "Init"
        getTopMovies()
    }

    fun getTopMovies() {
        viewModelScope.launch() {
            try {
                _status.value = "Trying"
                _movies.value = MoviesApi.retrofitService.getTopMovies().movies
                _status.value = "Success"
                Log.d("TopMoviesViewModel", "Success: first title = ${_movies.value!![0].title}")
            } catch (e: Exception) {
                Log.d("TopMoviesViewModel", "Failed: ${e.message}")
                _status.value = e.message
            }
        }
    }

}