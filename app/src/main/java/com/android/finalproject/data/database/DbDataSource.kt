package com.android.finalproject.data.database

import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.data.model.User

interface DbDataSource {
    suspend fun getAllMatchedUser(email: String): List<User>
    suspend fun addUser(user:User): Long

    suspend fun getFavMovieList(): List<Movie>
    suspend fun addMovieToFav(movie: Movie): Long
    suspend fun removeMovieFromFav(movie: Movie): Long

    suspend fun getFavSeriesList(): List<TvShow>
    suspend fun addSeriesToFav(series: TvShow): Long
    suspend fun removeSeriesFromFav(series: TvShow): Long
}