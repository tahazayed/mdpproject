package com.android.finalproject.ui.auth.signin

sealed class SignInResponseState {
    data class Loading(val loading: Boolean) : SignInResponseState()
    object Success : SignInResponseState()
    object Failure : SignInResponseState()
}
