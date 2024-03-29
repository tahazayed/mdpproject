package com.android.finalproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.finalproject.data.database.typeConverter.DateTypeConverter
import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.data.model.User

@Database(
    entities = [User::class, Movie::class, TvShow::class], version = 1
)
@TypeConverters(
    DateTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao

    companion object {
        private const val DB_NAME = "movie_app.db"

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}