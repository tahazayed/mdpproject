package com.android.finalproject.data.remote

import com.android.finalproject.data.model.MoviesList
import com.android.finalproject.data.model.TvShowsList
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getBaseAppResponse(): Response<String>
    suspend fun getPopularMovies(): Response<MoviesList>
    suspend fun getPopularTvShows(): Response<TvShowsList>
    suspend fun getDiscoverMovies(
        sortBy: String
    ): Response<MoviesList>

    suspend fun getDiscoverTvShows(
        sortBy: String
    ): Response<TvShowsList>
}