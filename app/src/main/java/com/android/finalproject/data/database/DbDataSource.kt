package com.android.finalproject.data.database

import com.android.finalproject.data.model.BaseAppResponse

interface DbDataSource {
    suspend fun getAllDataInTable(): BaseAppResponse
}