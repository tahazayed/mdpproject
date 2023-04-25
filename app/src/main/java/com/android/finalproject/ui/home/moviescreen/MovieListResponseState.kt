package com.android.finalproject.ui.home.moviescreen

import com.android.finalproject.data.model.MoviesList

sealed class MovieListResponseState {
    data class Success(val response: MoviesList) : MovieListResponseState()
    data class Loading(val loading: Boolean) : MovieListResponseState()
    data class Failure(val msg: String) : MovieListResponseState()
}
