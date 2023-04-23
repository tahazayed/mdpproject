package com.android.finalproject.data

import com.android.finalproject.data.model.User
import com.android.finalproject.data.repositories.Repository
import com.android.finalproject.util.FirebaseUtils


class SessionManager(private val repository: Repository) {
    private var user: User? = null

    companion object {
        private var instance: SessionManager? = null
        fun getInstance(repository: Repository) = synchronized(this) {
            if (instance == null)
                instance = SessionManager(repository)
            instance
        }
    }

    fun current(): User? {
        return user
    }


    fun setActiveSession(user: User) {
        this.user = user
    }

    fun logout() {
        user = null
        repository.logOut()
    }

}