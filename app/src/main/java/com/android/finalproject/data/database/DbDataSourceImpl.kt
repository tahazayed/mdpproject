package com.android.finalproject.data.database

import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.data.model.User


class DbDataSourceImpl(private val appDatabase: AppDatabase) : DbDataSource {
    override suspend fun getAllMatchedUser(email: String) =
        appDatabase.userDao().getAllMatchedUser(email)

    override suspend fun addUser(user: User) =
        appDatabase.userDao().addUser(user)

    override suspend fun getFavMovieList() =
        appDatabase.movieDao().getFavMovieList()

    override suspend fun addMovieToFav(movie: Movie) =
        appDatabase.movieDao().addMovieToFav(movie)

    override suspend fun removeMovieFromFav(movie: Movie) =
        appDatabase.movieDao().removeMovieFromFav(movie)

    override suspend fun getFavSeriesList() =
        appDatabase.seriesDao().getFavSeriesList()

    override suspend fun addSeriesToFav(series: TvShow) =
        appDatabase.seriesDao().addSeriesToFav(series)

    override suspend fun removeSeriesFromFav(series: TvShow) =
        appDatabase.seriesDao().removeSeriesFromFav(series)

    override suspend fun deleteMyAccount(user: User) =
        appDatabase.userDao().deleteMyAccount(user)

    override suspend fun deleteAllFav() {
        appDatabase.movieDao().removeAllFav()
        appDatabase.seriesDao().removeAllFav()
    }
}