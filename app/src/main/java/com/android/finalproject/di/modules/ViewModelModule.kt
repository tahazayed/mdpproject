package com.android.finalproject.di.modules

import com.android.finalproject.ui.landing.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
}