package com.android.finalproject.data.sharedpref

import com.android.finalproject.data.model.BaseAppResponse


interface PrefDataSource {

    fun getToken(): String
    fun setToken(token: String)

    fun getSharedPrefBaseAppResponse(): BaseAppResponse?
    fun setSharedPrefBaseAppResponse(baseAppResponse: BaseAppResponse)

    fun logOut()
}