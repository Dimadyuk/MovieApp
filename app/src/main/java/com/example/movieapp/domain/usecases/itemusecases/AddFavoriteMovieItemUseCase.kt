package com.example.movieapp.domain.usecases.itemusecases

import com.example.movieapp.domain.MovieRepository

class AddFavoriteMovieItemUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieItemId: Int) = repository.addFavoriteMovieItem(movieItemId)
}