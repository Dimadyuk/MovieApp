package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieRepository

class LoadPopularMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.loadPopularMovies()

}