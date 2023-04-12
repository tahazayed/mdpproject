package com.android.finalproject.data.repositories


import com.android.finalproject.data.database.DbDataSource
import com.android.finalproject.data.model.APIResult
import com.android.finalproject.data.model.BaseAppResponse
import com.android.finalproject.data.raw.RawDataSource
import com.android.finalproject.data.sharedpref.PrefDataSource

interface Repository : PrefDataSource, DbDataSource, RawDataSource {

    suspend fun getBaseAppResponse(): APIResult<BaseAppResponse>
    fun dummyOffline(): APIResult<String>
}
