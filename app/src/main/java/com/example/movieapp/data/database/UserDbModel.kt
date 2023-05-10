package com.example.movieapp.data.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.data.mapper.ListConverter

@Entity(
    tableName = "register_users_table",
    indices = [Index(value = ["login"], unique = true)]
)
@TypeConverters(ListConverter::class)
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val login: String,
    val password: String,
    val name: String,
    val surname: String,
    val favoritesMovies: List<Int>
)