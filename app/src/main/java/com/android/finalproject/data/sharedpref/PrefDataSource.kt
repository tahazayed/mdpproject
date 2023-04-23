package com.android.finalproject.data.sharedpref



interface PrefDataSource {

    fun getToken(): String
    fun setToken(token: String)

    fun getSharedPrefBaseAppResponse(): String?
    fun setSharedPrefBaseAppResponse(baseAppResponse: String)

    fun logOut()
}