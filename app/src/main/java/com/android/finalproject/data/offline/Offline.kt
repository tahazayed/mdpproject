package com.android.finalproject.data.offline

import com.android.finalproject.data.model.APIResult

interface Offline {
    fun dummyOffline(): APIResult<String>
}