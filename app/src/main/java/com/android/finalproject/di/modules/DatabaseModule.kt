package com.android.finalproject.di.modules

import com.android.finalproject.data.database.AppDatabase
import com.android.finalproject.data.database.DbDataSource
import com.android.finalproject.data.database.DbDataSourceImpl

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase.getInstance(androidContext()) }

    single<DbDataSource> { DbDataSourceImpl(get()) }
}