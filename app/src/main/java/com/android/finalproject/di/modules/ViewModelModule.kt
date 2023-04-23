package com.android.finalproject.di.modules

import com.android.finalproject.ui.auth.signin.SignInViewModel
import com.android.finalproject.ui.auth.signup.SignUpViewModel
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}