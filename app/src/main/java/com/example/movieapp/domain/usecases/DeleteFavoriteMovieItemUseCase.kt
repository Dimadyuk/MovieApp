package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieRepository

class DeleteFavoriteMovieItemUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieItemId: Int) = repository.deleteFavoriteMovieItem(movieItemId)
}