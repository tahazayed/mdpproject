package com.android.finalproject.ui.home.moviescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.finalproject.data.SessionManager
import com.android.finalproject.data.model.*
import com.android.finalproject.data.repositories.Repository
import com.android.finalproject.ui.base.BaseViewModel
import com.android.finalproject.ui.home.seriesscreen.SeriesListResponseState
import com.android.finalproject.ui.home.seriesscreen.SeriesVideoResponseState
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

    private var _movieVideoResponseState = Channel<MovieVideoResponseState>(Channel.BUFFERED)
    val movieVideoResponseState get() = _movieVideoResponseState.receiveAsFlow()

    private var _seriesVideoResponseState = Channel<SeriesVideoResponseState>(Channel.BUFFERED)
    val seriesVideoResponseState get() = _seriesVideoResponseState.receiveAsFlow()

    val user = MutableLiveData<User>()

    fun getDiscoverMovie(sort: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {

            val favList = repository.getFavMovieList()

            _movieListResponseState.trySend(MovieListResponseState.Loading(true))
            val response = repository.getDiscoverMovies(sort)

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
                        _movieListResponseState.trySend(
                            MovieListResponseState.Success(
                                it.results,
                                favList
                            )
                        )
                    }
                }
            }
        }
    }

    fun getFavMovie(sort: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val favList = repository.getFavMovieList()
            _movieListResponseState.trySend(MovieListResponseState.Success(favList, favList))
        }
    }

    fun searchForMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {

            val favList = repository.getFavMovieList()

            _movieListResponseState.trySend(MovieListResponseState.Loading(true))
            val response = repository.searchMovies(query)

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
                        _movieListResponseState.trySend(
                            MovieListResponseState.Success(
                                it.results,
                                favList
                            )
                        )
                    }
                }
            }
        }
    }

    fun searchForSeries(query: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {

            val favList = repository.getFavSeriesList()

            _seriesListResponseState.trySend(SeriesListResponseState.Loading(true))
            val response = repository.searchSeries(query)

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
                        _seriesListResponseState.trySend(
                            SeriesListResponseState.Success(
                                it.results,
                                favList
                            )
                        )
                    }
                }
            }
        }
    }

    fun getDiscoverSeries(sort: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val favList = repository.getFavSeriesList()


            _seriesListResponseState.trySend(SeriesListResponseState.Loading(true))
            val response = repository.getDiscoverTvShows(sort)

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
                        _seriesListResponseState.trySend(
                            SeriesListResponseState.Success(
                                it.results,
                                favList
                            )
                        )
                    }
                }
            }
        }
    }

    fun getFavSeries(sort: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val favList = repository.getFavSeriesList()
            _seriesListResponseState.trySend(SeriesListResponseState.Success(favList, favList))

        }
    }

    fun updateFavItem(movie: Movie, addToFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (addToFav)
                repository.addMovieToFav(movie)
            else
                repository.removeMovieFromFav(movie)
        }
    }

    fun updateFavItem(series: TvShow, addToFav: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (addToFav)
                repository.addSeriesToFav(series)
            else
                repository.removeSeriesFromFav(series)
        }
    }

    fun getProfileInfo() {
        user.postValue(SessionManager.getInstance(repository)?.current())
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFav()
            repository.logOut()
        }
    }

    fun deleteMyAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            SessionManager.getInstance(repository)?.current()?.let { user ->
                repository.deleteMyAccount(user)
            }
            repository.deleteAllFav()
            repository.logOut()
        }
    }

    fun getMovieVideos(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovieVideos(movieId)
            when (response) {
                is APIResult.Failure -> {
                    response.error?.message?.let {
                        _movieVideoResponseState.trySend(MovieVideoResponseState.Failure(it))
                    }
                }
                is APIResult.Success -> {
                    response.body?.let {
                        _movieVideoResponseState.trySend(MovieVideoResponseState.Success(it))
                    }
                }
            }
        }
    }

    fun getSeriesVideos(seriesId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getTvShowVideos(seriesId)
            when (response) {
                is APIResult.Failure -> {
                    response.error?.message?.let {
                        _seriesVideoResponseState.trySend(SeriesVideoResponseState.Failure(it))
                    }
                }
                is APIResult.Success -> {
                    response.body?.let {
                        _seriesVideoResponseState.trySend(SeriesVideoResponseState.Success(it))
                    }
                }
            }
        }
    }
}