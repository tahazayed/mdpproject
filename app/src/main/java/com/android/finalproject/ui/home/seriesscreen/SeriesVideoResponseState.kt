package com.android.finalproject.ui.home.seriesscreen

import com.android.finalproject.data.model.VideosList

sealed class SeriesVideoResponseState{
    data class Success(val movieList: VideosList) : SeriesVideoResponseState()
    data class Failure(val msg: String) : SeriesVideoResponseState()
}
