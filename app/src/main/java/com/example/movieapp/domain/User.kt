package com.example.movieapp.domain

data class User(
    val userId: Int,
    val login: String,
    val password: String,
    val name: String,
    val surname: String,
    val favoritesMovies: List<Int>,
    val imageUrl: String,
)