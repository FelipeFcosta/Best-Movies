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

    private val _statusMovieList = MutableLiveData<MoviesApi.MovieStatus>()
    val statusMovieList: MutableLiveData<MoviesApi.MovieStatus> = _statusMovieList
    private val _statusDetailedMovie = MutableLiveData<MoviesApi.MovieStatus>()
    val statusDetailedMovie: MutableLiveData<MoviesApi.MovieStatus> = _statusDetailedMovie

    private var _movieId: Int = 0

    private var _lastRequest = MoviesApi.MovieLastRequest.NONE

    init {
        getTopMovies()
    }

    fun getTopMovies() {
        _lastRequest = MoviesApi.MovieLastRequest.TOP_MOVIES
        viewModelScope.launch() {
            _statusMovieList.value = MoviesApi.MovieStatus.LOADING
            try {
                _movies.value = MoviesApi.retrofitService.getTopMovies().movies
                _statusMovieList.value = MoviesApi.MovieStatus.DONE
            } catch (e: Exception) {
                _statusMovieList.value = MoviesApi.MovieStatus.ERROR
            }
        }
    }

    fun getDetailedMovie() {
        _lastRequest = MoviesApi.MovieLastRequest.DETAILED_MOVIE
        viewModelScope.launch() {
            _statusDetailedMovie.value = MoviesApi.MovieStatus.LOADING
            try {
                _movie.value = MoviesApi.retrofitService.getMovie(_movieId)
                // set genreIds based on the getTopMovies request
                _movie.value?.genreIds = _movies.value?.single {it.id == _movieId}?.genreIds
                _statusDetailedMovie.value = MoviesApi.MovieStatus.DONE
            } catch (e: Exception) {
                _statusDetailedMovie.value = MoviesApi.MovieStatus.ERROR
            }
        }
    }


    fun repeatMostRecentRequest() {
        if (_lastRequest == MoviesApi.MovieLastRequest.DETAILED_MOVIE) {
            getDetailedMovie()
        } else {
            getTopMovies()
        }
    }


    fun setNewMovieId(movieId: Int) {
        _movieId = movieId
    }
}