package com.android.finalproject.ui.auth.signup


sealed class SignUpResponseState{
    data class Loading(val loading: Boolean) : SignUpResponseState()
    object Success : SignUpResponseState()
    object Failure : SignUpResponseState()
}
