package com.example.movieapp.data.mapper

import com.example.movieapp.data.database.UserDbModel
import com.example.movieapp.domain.User

class UserMapper {

    fun mapDbModelToUser(dbModel: UserDbModel) = User(
        userId = dbModel.userId,
        login = dbModel.login,
        password = dbModel.password,
        name = dbModel.name,
        surname = dbModel.surname,
        favoritesMovies = dbModel.favoritesMovies
    )

    fun mapUserToDbModel(user: User) = UserDbModel(
        userId = user.userId,
        login = user.login,
        password = user.password,
        name = user.name,
        surname = user.surname,
        favoritesMovies = user.favoritesMovies
    )
}