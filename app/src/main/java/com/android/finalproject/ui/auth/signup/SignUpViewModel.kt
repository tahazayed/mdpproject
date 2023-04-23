package com.android.finalproject.ui.auth.signup

import androidx.lifecycle.viewModelScope
import com.android.finalproject.data.SessionManager
import com.android.finalproject.data.model.User
import com.android.finalproject.data.repositories.Repository
import com.android.finalproject.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpViewModel(val repository: Repository) : BaseViewModel() {


    private var _signUpFormResponseState = Channel<SignUpFormResponseState>(Channel.BUFFERED)
    val signUpFormResponseState get() = _signUpFormResponseState.receiveAsFlow()


    private var _signUpResponseState = Channel<SignUpResponseState>(Channel.BUFFERED)
    val signUpResponseState get() = _signUpResponseState.receiveAsFlow()


    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.isNullOrEmpty()) _signUpFormResponseState.trySend(SignUpFormResponseState.NameIsEmpty)
            else if (email.isNullOrEmpty()) _signUpFormResponseState.trySend(SignUpFormResponseState.EmailIsEmpty)
            else if (password.isNullOrEmpty()) _signUpFormResponseState.trySend(
                SignUpFormResponseState.PasswordIsEmpty
            )
            else _signUpFormResponseState.trySend(
                SignUpFormResponseState.Proceed(
                    name, email, password
                )
            )
        }
    }

    fun performSignUp(name: String, email: String, password: String) {
        _signUpResponseState.trySend(SignUpResponseState.Loading(true))

        viewModelScope.launch(Dispatchers.Default) {
            val user = User(name = name, email = email, password = password)

            val response = repository.addUser(user)

            if (response == 1L) {
                _signUpResponseState.trySend(SignUpResponseState.Loading(false))
                SessionManager.getInstance(repository)?.setActiveSession(user)
                _signUpResponseState.trySend(SignUpResponseState.Success)
            } else {
                _signUpResponseState.trySend(SignUpResponseState.Loading(false))
                _signUpResponseState.trySend(SignUpResponseState.Failure)
            }
        }
    }
}