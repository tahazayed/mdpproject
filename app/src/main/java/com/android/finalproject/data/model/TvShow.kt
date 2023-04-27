package com.android.finalproject.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "series")
data class TvShow(
    val backdrop_path: String?,
    val first_air_date: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val original_language: String?,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TvShow

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}