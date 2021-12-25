package com.example.bestmovies.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.bestmovies.models.Movie
import com.example.bestmovies.network.MoviesApi
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: MutableLiveData<List<Movie>> = _movies

    private val _movie = MutableLiveData<Movie>()
    val movie: MutableLiveData<Movie> = _movie

    private val _status = MutableLiveData<MoviesApi.MovieStatus>()
    val status: MutableLiveData<MoviesApi.MovieStatus> = _status

    private var _movieId: Int = 0

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
                Log.d("MoviesViewModel", "Failed: ${e.message}")
                _status.value = MoviesApi.MovieStatus.ERROR
            }
        }
    }

    fun getDetailedMovie() {
        viewModelScope.launch() {
            _status.value = MoviesApi.MovieStatus.LOADING
            try {
                _movie.value = MoviesApi.retrofitService.getMovie(_movieId)
                // set genreIds based on the getTopMovies request
                _movie.value?.genreIds = _movies.value?.single {it.id == _movieId}?.genreIds

                _status.value = MoviesApi.MovieStatus.DONE
            } catch (e: Exception) {
                Log.d("MoviesViewModel", "Failed: ${e.message}")
                _status.value = MoviesApi.MovieStatus.ERROR
            }
        }
    }


    fun setNewMovieId(movieId: Int) {
        _movieId = movieId
    }
}