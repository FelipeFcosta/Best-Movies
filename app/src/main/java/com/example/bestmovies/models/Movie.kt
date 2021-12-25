package com.example.bestmovies.models

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @Json(name="vote_average") val voteAverage: Double,
    @Json(name="vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name="genre_ids") var genreIds: List<Int>?,
    @Json(name="release_date") val releaseDate: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    // only in the specific id request
    @Json(name = "production_countries") val countries: List<ProductionCountry>?,
    val budget: Int?,
    val runtime: Int?,
    val revenue: Int?
    )
