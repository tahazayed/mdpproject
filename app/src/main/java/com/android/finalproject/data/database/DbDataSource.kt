package com.android.finalproject.data.database

import com.android.finalproject.data.model.User

interface DbDataSource {
    suspend fun getAllMatchedUser(email: String): List<User>
    suspend fun addUser(user:User): Long

}