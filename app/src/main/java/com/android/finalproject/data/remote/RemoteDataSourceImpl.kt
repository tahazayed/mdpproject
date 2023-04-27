package com.android.finalproject.data.remote

import com.android.finalproject.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImp(private val remoteApi: MovieAppAPI) : RemoteDataSource {

    override suspend fun getBaseAppResponse() = withContext(Dispatchers.IO) {
        remoteApi.getBaseAppResponse()
    }

    override suspend fun getPopularMovies(
    ) = withContext(Dispatchers.IO) {
        remoteApi.getPopularMovies(apiKey = BuildConfig.API_KEY)
    }

    override suspend fun getPopularTvShows(
    ) = withContext(Dispatchers.IO) {
        remoteApi.getPopularTvShows(apiKey = BuildConfig.API_KEY)
    }

    override suspend fun getDiscoverMovies(
        sortBy: String
    ) = withContext(Dispatchers.IO) {
        remoteApi.getDiscoverMovies(apiKey = BuildConfig.API_KEY, sortBy = sortBy)
    }

    override suspend fun searchMovies(
        query: String
    ) = withContext(Dispatchers.IO) {
        remoteApi.searchMovies(apiKey = BuildConfig.API_KEY, query = query)
    }

    override suspend fun searchSeries(
        query: String
    ) = withContext(Dispatchers.IO) {
        remoteApi.searchTvShows(apiKey = BuildConfig.API_KEY, query = query)
    }

    override suspend fun getDiscoverTvShows(
        sortBy: String
    ) = withContext(Dispatchers.IO) {
        remoteApi.getDiscoverTvShows(apiKey = BuildConfig.API_KEY, sortBy = sortBy)
    }

    override suspend fun getMovieDetails(movieId: Int) = withContext(Dispatchers.IO) {
        remoteApi.getMovieDetails(apiKey = BuildConfig.API_KEY, movieId = movieId)
    }

    override suspend fun getTvShowDetails(tvId: Int) = withContext(Dispatchers.IO) {
        remoteApi.getTvShowDetails(apiKey = BuildConfig.API_KEY, tvId = tvId)
    }

    override suspend fun getMovieVideos(movieId: Int) = withContext(Dispatchers.IO) {
        remoteApi.getMovieVideos(apiKey = BuildConfig.API_KEY, movieId = movieId)
    }

    override suspend fun getTvShowVideos(tvId: Int) = withContext(Dispatchers.IO) {
        remoteApi.getTvShowVideos(apiKey = BuildConfig.API_KEY, tvId = tvId)
    }
}