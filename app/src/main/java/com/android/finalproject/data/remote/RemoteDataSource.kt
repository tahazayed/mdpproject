package com.android.finalproject.data.remote

import retrofit2.Response

interface RemoteDataSource {

    suspend fun getBaseAppResponse(): Response<String>
}