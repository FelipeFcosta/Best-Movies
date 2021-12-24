package com.example.bestmovies.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestmovies.models.Movie
import com.example.bestmovies.network.MoviesApi
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: MutableLiveData<List<Movie>> = _movies

    private val _status = MutableLiveData<MoviesApi.MovieStatus>()
    val status: MutableLiveData<MoviesApi.MovieStatus> = _status

    private var _newMovieId: Int = 0

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

    fun setNewMovieId(movieId: Int) {
        _newMovieId = movieId
    }
}