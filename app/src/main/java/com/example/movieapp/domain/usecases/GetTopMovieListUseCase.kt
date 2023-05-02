package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieRepository

class GetTopMovieListUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getTopMoviesList()
}