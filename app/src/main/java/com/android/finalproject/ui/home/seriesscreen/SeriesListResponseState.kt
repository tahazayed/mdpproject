package com.android.finalproject.ui.home.seriesscreen

import com.android.finalproject.data.model.TvShow
import com.android.finalproject.data.model.TvShowsList

sealed class SeriesListResponseState {
    data class Success(val seriesList: List<TvShow>, val favList: List<TvShow>) :
        SeriesListResponseState()

    data class Loading(val loading: Boolean) : SeriesListResponseState()

    data class Failure(val msg: String) : SeriesListResponseState()
}

