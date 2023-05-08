package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.MovieRepository

class GetFavoriteMovieItemUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieItemId: Int): MovieItem? = repository.getFavoriteMovieItem(movieItemId)

}
