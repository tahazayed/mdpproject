package com.android.finalproject.data.remote

import com.android.finalproject.data.model.TvGenreList
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.data.model.TvShowsList
import com.android.finalproject.data.remote.BaseMovieDbApiService.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowsApiService : BaseMovieDbApiService {

    @GET("tv/{tvId}")
    suspend fun getTvShowDetails(
        @Path("tvId") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Response<TvShow>

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowsList>

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowsList>

    @GET("genre/tv/list")
    suspend fun getTvShowsGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): Response<TvGenreList>
}
