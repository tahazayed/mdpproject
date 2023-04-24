package com.android.finalproject.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class MoviesList(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
):Parcelable