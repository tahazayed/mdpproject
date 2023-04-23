package com.android.finalproject.ui.auth.signin

sealed class SignInFormResponseState {
    object EmailIsEmpty : SignInFormResponseState()
    object PasswordIsEmpty : SignInFormResponseState()
    data class Proceed(val email: String, val password: String) : SignInFormResponseState()
}
