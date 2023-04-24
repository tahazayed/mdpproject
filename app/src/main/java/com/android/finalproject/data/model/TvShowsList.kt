package com.android.finalproject.data.model

data class TvShowsList(
    val page: Int,
    val results: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)