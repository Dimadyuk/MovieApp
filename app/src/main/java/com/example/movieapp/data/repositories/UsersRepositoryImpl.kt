package com.example.movieapp.data.repositories

import android.app.Application
import com.example.movieapp.data.database.AppDatabase
import com.example.movieapp.data.mapper.UserMapper
import com.example.movieapp.domain.User
import com.example.movieapp.domain.UsersRepository

class UsersRepositoryImpl(application: Application) : UsersRepository {

    private val usersDao = AppDatabase.getInstance(application).usersDao()
    private val mapper = UserMapper()

    override suspend fun addNewUser(newUser: User) {
        val newUserDbModel = mapper.mapUserToDbModel(newUser)
        usersDao.insertUser(newUserDbModel)
    }

    override suspend fun getUser(login: String): User? {
        val userDbModel = usersDao.getUser(login)
        return userDbModel?.let { mapper.mapDbModelToUser(userDbModel) }
    }
}