package com.android.finalproject.data.remote

import com.android.finalproject.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieAppAPI {

    @POST("dummy/getBaseAppResponse")
    suspend fun getBaseAppResponse(): Response<String>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<Movie>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesList>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesList>

    @GET("genre/movie/list")
    suspend fun getMoviesGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
    ): Response<MovieGenreList>


    @GET("tv/{tvId}")
    suspend fun getTvShowDetails(
        @Path("tvId") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<TvShow>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowsList>

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowsList>

    @GET("genre/tv/list")
    suspend fun getTvShowsGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
    ): Response<TvGenreList>

    @GET("discover/tv")
    suspend fun discoverTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String
    ): Response<TvShowsList>

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String
    ): Response<MoviesList>
}