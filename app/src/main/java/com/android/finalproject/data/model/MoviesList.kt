package com.android.finalproject.data.model

data class MoviesList(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)