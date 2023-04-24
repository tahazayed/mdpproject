package com.android.finalproject.data.remote

import com.android.finalproject.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImp(private val remoteApi: MovieAppAPI) : RemoteDataSource {

    override suspend fun getBaseAppResponse() = withContext(Dispatchers.IO) {
        remoteApi.getBaseAppResponse()
    }

    override suspend fun getDiscoverMovies(
        sortBy: String
    ) = withContext(Dispatchers.IO) {
        remoteApi.getDiscoverMovies(apiKey = BuildConfig.API_KEY, sortBy = sortBy)
    }

    override suspend fun getDiscoverTvShows(
        sortBy: String
    ) = withContext(Dispatchers.IO) {
        remoteApi.getDiscoverTvShows(apiKey = BuildConfig.API_KEY, sortBy = sortBy)
    }
}