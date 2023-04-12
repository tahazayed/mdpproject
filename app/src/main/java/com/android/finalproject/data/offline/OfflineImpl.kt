package com.android.finalproject.data.offline

import android.content.Context
import com.android.finalproject.data.model.APIResult

class OfflineImpl(private val context: Context) : Offline {

    override fun dummyOffline(): APIResult<String> {
        val dummyString = "dummy"
        return APIResult.success(dummyString)
    }
}