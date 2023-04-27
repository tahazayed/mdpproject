package com.android.finalproject.data.repositories


import com.android.finalproject.BuildConfig
import com.android.finalproject.data.database.DbDataSource
import com.android.finalproject.data.model.APIResult
import com.android.finalproject.data.model.MoviesList
import com.android.finalproject.data.model.TvShowsList
import com.android.finalproject.data.model.VideosList
import com.android.finalproject.data.raw.RawDataSource
import com.android.finalproject.data.sharedpref.PrefDataSource
import com.android.finalproject.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface Repository : PrefDataSource, DbDataSource, RawDataSource {

    suspend fun getBaseAppResponse(): APIResult<String>

    fun dummyOffline(): APIResult<String>

    suspend fun getDiscoverMovies(
        sortBy: String = Constants.EMPTY_STRING
    ): APIResult<MoviesList>

    suspend fun getPopularMovies(): APIResult<MoviesList>

    suspend fun getPopularTvShows(): APIResult<TvShowsList>

    suspend fun getDiscoverTvShows(
        sortBy: String = Constants.EMPTY_STRING
    ): APIResult<TvShowsList>

    suspend fun searchMovies(
        query: String
    ): APIResult<MoviesList>

    suspend fun searchSeries(
        query: String
    ): APIResult<TvShowsList>

    suspend fun getMovieVideos(
        movieId: Int
    ): APIResult<VideosList>

    suspend fun getTvShowVideos(
        tvId: Int
    ): APIResult<VideosList>
}
