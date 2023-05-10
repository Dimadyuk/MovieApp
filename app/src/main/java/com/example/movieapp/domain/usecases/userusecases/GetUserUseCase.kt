package com.example.movieapp.domain.usecases.userusecases

import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.MovieRepository

class GetUserUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieItemId: Int): MovieItem = repository.getMovieItem(movieItemId)

}
