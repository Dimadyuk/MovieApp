package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieRepository

class GetPopularMovieListUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getPopularMoviesList()
}