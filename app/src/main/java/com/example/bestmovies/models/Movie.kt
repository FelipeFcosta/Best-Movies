package com.example.bestmovies.models

import com.squareup.moshi.Json

data class Movie(
    val id: String,
    val title: String,
    @Json(name="vote_average") val voteAverage: Double,
    @Json(name="vote_count") val voteCount: Double,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name="genre_ids") val genreIds: List<Int>,
    @Json(name="genres") val genres: Map<Int, String>,
    @Json(name="release_date") val releaseDate: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "production_countries") val countries: List<String>,
    val budget: Int,
    val revenue: Int,
    val overview: String
    )
