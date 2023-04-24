package com.android.finalproject.data.remote

import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.MovieGenreList
import com.android.finalproject.data.model.MoviesList
import com.android.finalproject.data.remote.BaseMovieDbApiService.Companion.API_KEY

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService : BaseMovieDbApiService {

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Response<Movie>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesList>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesList>

    @GET("genre/movie/list")
    suspend fun getMoviesGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): Response<MovieGenreList>
}
