package com.example.movieapp.domain.usecases.itemusecases

import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.MovieRepository

class GetMovieItemUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movieItemId: Int): MovieItem = repository.getMovieItem(movieItemId)

}
