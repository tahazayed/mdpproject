package com.android.finalproject.data.database

class DbDataSourceImpl(private val appDatabase: AppDatabase) : DbDataSource {
    override suspend fun getAllDataInTable() = appDatabase.baseAppDao().getAllDataInTable()
}