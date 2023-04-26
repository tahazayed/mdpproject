package com.android.finalproject.data.database

import androidx.room.*
import com.android.finalproject.data.model.TvShow

@Dao
interface SeriesDao {
    @Query("SELECT * FROM series")
    fun getFavSeriesList(): List<TvShow>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSeriesToFav(series: TvShow): Long

    @Delete
    fun removeSeriesFromFav(series: TvShow): Long
}