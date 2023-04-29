package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieRepository

class GetPopularMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getPopularMovies()

}