package com.android.finalproject.data.repositories


import com.android.finalproject.data.database.DbDataSource
import com.android.finalproject.data.model.APIResult
import com.android.finalproject.data.model.MoviesList
import com.android.finalproject.data.model.TvShowsList
import com.android.finalproject.data.raw.RawDataSource
import com.android.finalproject.data.sharedpref.PrefDataSource
import retrofit2.Response

interface Repository : PrefDataSource, DbDataSource, RawDataSource {

    suspend fun getBaseAppResponse(): APIResult<String>
    fun dummyOffline(): APIResult<String>

    suspend fun getDiscoverMovies(
        sortBy: String
    ): APIResult<MoviesList>

    suspend fun getDiscoverTvShows(
        sortBy: String
    ): APIResult<TvShowsList>

}
