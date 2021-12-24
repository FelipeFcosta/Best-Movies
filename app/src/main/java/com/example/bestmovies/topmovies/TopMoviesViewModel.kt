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

    private val _status = MutableLiveData<MoviesApi.MovieStatus>()
    val status: MutableLiveData<MoviesApi.MovieStatus> = _status

    private val _genres = MutableLiveData<List<String>>()
    val genres: MutableLiveData<List<String>> = _genres

    init {
        getTopMovies()
    }

    fun getTopMovies() {
        viewModelScope.launch() {
            _status.value = MoviesApi.MovieStatus.LOADING
            try {
                _movies.value = MoviesApi.retrofitService.getTopMovies().movies
                _status.value = MoviesApi.MovieStatus.DONE
            } catch (e: Exception) {
                Log.d("TopMoviesViewModel", "Failed: ${e.message}")
                _status.value = MoviesApi.MovieStatus.ERROR
            }
        }
    }

}