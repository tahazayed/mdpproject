package com.android.finalproject.data.remote

import com.android.finalproject.data.model.BaseAppResponse
import retrofit2.Response
import retrofit2.http.POST


interface BaseAppAPI {

    @POST("dummy/getBaseAppResponse")
    suspend fun getBaseAppResponse(): Response<BaseAppResponse>
}