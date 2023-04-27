package com.android.finalproject.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class VideosList(
    val id: Int,
    val results: List<Video>?
) : Parcelable