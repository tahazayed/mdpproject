package com.android.finalproject.data.remote

import com.android.finalproject.data.model.BaseAppResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getBaseAppResponse(): Response<BaseAppResponse>
}