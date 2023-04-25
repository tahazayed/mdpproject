package com.android.finalproject.ui.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.finalproject.data.SessionManager
import com.android.finalproject.data.model.User
import com.android.finalproject.data.repositories.Repository
import com.android.finalproject.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: Repository) : BaseViewModel() {

    private var _signInFormResponseState = Channel<SignInFormResponseState>(Channel.BUFFERED)
    val signInFormResponseState get() = _signInFormResponseState.receiveAsFlow()

    var str: MutableLiveData<String>?= null

    private var _signInResponseState = Channel<SignInResponseState>(Channel.BUFFERED)
    val signInResponseState get() = _signInResponseState.receiveAsFlow()


    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (email.isNullOrEmpty()) _signInFormResponseState.trySend(SignInFormResponseState.EmailIsEmpty)
            else if (password.isNullOrEmpty()) _signInFormResponseState.trySend(
                SignInFormResponseState.PasswordIsEmpty
            )
            else _signInFormResponseState.trySend(SignInFormResponseState.Proceed(email, password))
        }
    }


    fun performSignIn(email: String, password: String) {
        _signInResponseState.trySend(SignInResponseState.Loading(true))


        viewModelScope.launch(Dispatchers.Default) {
            val users = repository.getAllMatchedUser(email)
            var user = User(email = email, password = password)

            if (users.contains(user)) {
                user = users.filter { it.email == email && it.password == password }[0]
                SessionManager.getInstance(repository)?.setActiveSession(user)
                _signInResponseState.trySend(SignInResponseState.Loading(false))
                _signInResponseState.trySend(SignInResponseState.Success)
            } else {
                _signInResponseState.trySend(SignInResponseState.Loading(false))
                _signInResponseState.trySend(SignInResponseState.Failure)
            }
        }
    }


}