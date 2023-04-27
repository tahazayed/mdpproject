package com.android.finalproject.ui.home.moviescreen

import com.android.finalproject.data.model.VideosList

sealed class MovieVideoResponseState{
    data class Success(val movieList: VideosList) : MovieVideoResponseState()
    data class Failure(val msg: String) : MovieVideoResponseState()
}
