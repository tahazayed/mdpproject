package com.android.finalproject.util

object Constants {
    const val TOKEN = "token"
    const val APP_VERSION = "V "
    const val MANAGE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 1
    const val READ_EXTERNAL_STORAGE_PERMISSION_REQUEST = 2
    const val EMPTY_STRING = ""
    const val POSTER_PATH_PRE_URL = "https://image.tmdb.org/t/p/w185"

    //sorting for movies
    const val MOVIE_MOST_POPULAR = "popularity.desc"
    const val MOVIE_TOP_RATED = "vote_average.desc"
    const val MOVIE_COMING_SOON = "release_date.desc"
    const val MOVIE_FAVORITE = "favorite"

    //sorting for tv show
    const val SERIES_MOST_POPULAR = "popularity.desc"
    const val SERIES_TOP_RATED = "vote_average.desc"
    const val SERIES_COMING_SOON = "first_air_date.desc"
    const val SERIES_FAVORITE = "favorite"
}