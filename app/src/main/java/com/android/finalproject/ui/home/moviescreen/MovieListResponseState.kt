package com.android.finalproject.ui.home.moviescreen

import com.android.finalproject.data.model.Movie

sealed class MovieListResponseState {
    data class Success(val movieList: List<Movie>,val favList: List<Movie>) : MovieListResponseState()
    data class Loading(val loading: Boolean) : MovieListResponseState()
    data class Failure(val msg: String) : MovieListResponseState()
}
