package com.example.bestmovies.models

import com.squareup.moshi.Json

data class Response(
    @Json(name="results") val movies: List<Movie>
    )