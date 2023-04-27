package com.android.finalproject.data.database

import androidx.room.*
import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email")
    fun getAllMatchedUser(email: String): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User): Long

    @Delete
    fun deleteMyAccount(user: User): Int
}