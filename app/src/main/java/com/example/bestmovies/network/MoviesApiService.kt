package com.example.bestmovies.network

import com.example.bestmovies.utilities.Constants
import com.example.bestmovies.models.Movie
import com.example.bestmovies.models.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()


interface MoviesApiService {

    @GET("movie/top_rated")
    suspend fun getTopMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Movie
}


// singleton
object MoviesApi {
    enum class MovieStatus { LOADING, ERROR, DONE }
    enum class MovieLastRequest { TOP_MOVIES, DETAILED_MOVIE, NONE }

    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}