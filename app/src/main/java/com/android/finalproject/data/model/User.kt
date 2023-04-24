package com.android.finalproject.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val email: String,
    val password: String
):Parcelable {


    override fun equals(other: Any?): Boolean {
        val user = other as User

        return (this.email.lowercase() == user.email.lowercase()
                && this.password.lowercase() == user.password.lowercase())
    }

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}
