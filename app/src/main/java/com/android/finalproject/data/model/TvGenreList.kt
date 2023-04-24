package com.android.finalproject.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class TvGenreList(
    val genres: List<TvGenre>
):Parcelable