package com.android.finalproject.ui.home.seriesscreen

import com.android.finalproject.data.model.TvShowsList

sealed class SeriesListResponseState {
    data class Success(val response: TvShowsList) : SeriesListResponseState()

    data class Loading(val loading: Boolean) : SeriesListResponseState()

    data class Failure(val msg: String) : SeriesListResponseState()
}
