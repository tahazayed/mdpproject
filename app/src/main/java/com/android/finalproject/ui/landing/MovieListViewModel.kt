package com.android.finalproject.ui.landing

import com.android.finalproject.data.repositories.Repository
import com.android.finalproject.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MovieListViewModel(
    private val repository: Repository,
) : BaseViewModel() {

    private var _movieListResponseState = Channel<MovieListResponseState>(Channel.BUFFERED)
    val movieListResponseState get() = _movieListResponseState.receiveAsFlow()

}