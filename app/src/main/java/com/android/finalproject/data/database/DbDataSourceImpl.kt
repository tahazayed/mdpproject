package com.android.finalproject.data.database

import com.android.finalproject.data.model.User


class DbDataSourceImpl(private val appDatabase: AppDatabase) : DbDataSource {
    override suspend fun getAllMatchedUser(email: String) =
        appDatabase.userDao().getAllMatchedUser(email)

    override suspend fun addUser(user: User) =
        appDatabase.userDao().addUser(user)


}