package com.android.finalproject.ui.auth.signup


sealed class SignUpFormResponseState {
    object NameIsEmpty : SignUpFormResponseState()
    object EmailIsEmpty : SignUpFormResponseState()
    object EmailIsNotValid : SignUpFormResponseState()
    object PasswordIsEmpty : SignUpFormResponseState()

    data class Proceed(val name: String, val email: String, val password: String) :
        SignUpFormResponseState()
}
