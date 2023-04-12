package com.android.finalproject.di.modules

import com.android.finalproject.data.offline.Offline
import com.android.finalproject.data.offline.OfflineImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val offlineModule = module{
    single<Offline> { OfflineImpl(androidApplication()) }
}