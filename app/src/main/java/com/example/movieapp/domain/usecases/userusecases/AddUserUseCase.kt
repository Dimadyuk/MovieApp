package com.example.movieapp.domain.usecases.userusecases

import com.example.movieapp.domain.User
import com.example.movieapp.domain.UsersRepository

class AddUserUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke(newUser: User) = repository.addNewUser(newUser)
}