package com.example.bestmovies.topmovies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestmovies.models.Movie
import com.example.bestmovies.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopMoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: MutableLiveData<List<Movie>> = _movies

    fun getTopMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.value = MoviesApi.retrofitService.getTopMovies()
                Log.d("TopMoviesViewModel", "Success: first title = ${_movies.value!![0].title}")
            } catch (e: Exception) {
                Log.d("TopMoviesViewModel", "Failed")
            }
        }
    }
}