package com.android.finalproject.ui.home.moviescreen

import androidx.lifecycle.viewModelScope
import com.android.finalproject.data.model.APIResult
import com.android.finalproject.data.repositories.Repository
import com.android.finalproject.ui.base.BaseViewModel
import com.android.finalproject.ui.home.seriesscreen.SeriesListResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository,
) : BaseViewModel() {

    private var _movieListResponseState = Channel<MovieListResponseState>(Channel.BUFFERED)
    val movieListResponseState get() = _movieListResponseState.receiveAsFlow()

    private var _seriesListResponseState = Channel<SeriesListResponseState>(Channel.BUFFERED)
    val seriesListResponseState get() = _seriesListResponseState.receiveAsFlow()

    fun getDiscoverMovie() {
        viewModelScope.launch(Dispatchers.IO + handler) {

            _movieListResponseState.trySend(MovieListResponseState.Loading(true))
            val response = repository.getDiscoverMovies("popularity.desc")

            when (response) {
                is APIResult.Failure -> {
                    _movieListResponseState.trySend(MovieListResponseState.Loading(false))
                    response.error?.message?.let {
                        _movieListResponseState.trySend(MovieListResponseState.Failure(it))
                    }

                }
                is APIResult.Success -> {
                    _movieListResponseState.trySend(MovieListResponseState.Loading(false))
                    response.body?.let {
                        _movieListResponseState.trySend(MovieListResponseState.Success(it))
                    }
                }
            }
        }
    }

    fun getDiscoverSeries() {
        viewModelScope.launch(Dispatchers.IO + handler) {

            _seriesListResponseState.trySend(SeriesListResponseState.Loading(true))
            val response = repository.getDiscoverTvShows("popularity.desc")

            when (response) {
                is APIResult.Failure -> {
                    _seriesListResponseState.trySend(SeriesListResponseState.Loading(false))
                    response.error?.message?.let {
                        _seriesListResponseState.trySend(SeriesListResponseState.Failure(it))
                    }

                }
                is APIResult.Success -> {
                    _seriesListResponseState.trySend(SeriesListResponseState.Loading(false))
                    response.body?.let {
                        _seriesListResponseState.trySend(SeriesListResponseState.Success(it))
                    }
                }
            }
        }
    }
}