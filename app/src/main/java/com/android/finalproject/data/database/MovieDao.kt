package com.android.finalproject.data.database

import androidx.room.*
import com.android.finalproject.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getFavMovieList(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovieToFav(movie: Movie): Long

    @Delete
    fun removeMovieFromFav(movie: Movie): Int
}