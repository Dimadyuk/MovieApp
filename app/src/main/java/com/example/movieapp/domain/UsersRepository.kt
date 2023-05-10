package com.example.movieapp.domain

interface UsersRepository {

    suspend fun addNewUser(newUser: User)

    suspend fun getUser(login: String): User?
}