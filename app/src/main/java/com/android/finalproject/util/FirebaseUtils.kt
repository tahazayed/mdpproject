package com.android.finalproject.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
}

fun prepareBearerToken(token: String): String {
    return "Bearer $token"
}

