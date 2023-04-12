package com.android.finalproject.di.modules

import com.android.finalproject.data.SessionManager
import org.koin.dsl.module

val appModule = module {
    single { SessionManager.getInstance(get()) }
}