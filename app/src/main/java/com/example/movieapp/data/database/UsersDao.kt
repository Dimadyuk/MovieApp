package com.example.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(newUser: UserDbModel)

    @Query("SELECT * FROM register_users_table WHERE login = :login")
    suspend fun getUser(login: String): UserDbModel?
}