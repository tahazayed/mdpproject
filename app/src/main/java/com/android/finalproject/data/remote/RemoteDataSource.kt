package com.android.finalproject.data.remote

import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.MoviesList
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.data.model.TvShowsList
import com.android.finalproject.data.model.VideosList
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

    suspend fun getMovieDetails(
        movieId: Int
    ): Response<Movie>

    suspend fun getTvShowDetails(
        tvId: Int
    ): Response<TvShow>


    suspend fun getMovieVideos(
        movieId: Int
    ): Response<VideosList>

    suspend fun getTvShowVideos(
        tvId: Int
    ): Response<VideosList>
}